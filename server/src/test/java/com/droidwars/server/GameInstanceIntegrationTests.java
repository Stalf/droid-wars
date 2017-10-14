package com.droidwars.server;

import com.droidwars.game.engine.GameInstanceServer;
import com.droidwars.game.engine.factory.game.AbstractGameInstanceFactory;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.hamcrest.io.FileMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestPropertySource(locations="classpath:test.properties")
@Slf4j
public class GameInstanceIntegrationTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private AbstractGameInstanceFactory gameInstanceFactory;

    @Autowired
    private ConfigProperties configProperties;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;
    private Path rootPath;

    @BeforeClass
    public void setUp() {
        rootPath = configProperties.getFileStorage().getRootPath();
    }

    @AfterClass
    public void cleanUp() throws IOException {
        if (rootPath.toFile().exists()) {
            FileUtils.forceDelete(rootPath.toFile());
        }
    }

    @Test
    public void simpleFactoryTest() {
        log.info("simpleFactoryTest");
        GameInstanceServer gameInstance = gameInstanceFactory.createGameInstance();
        gameInstance.run();

        checkBattleRecordExist(gameInstance);
    }

    private void checkBattleRecordExist(GameInstanceServer gameInstance) {
        Path battleFile = Paths.get(rootPath.toString(), String.format("%s.dat", gameInstance.getBattleId()));
        assertThat(battleFile.toFile(), FileMatchers.anExistingFile());
    }

    @Test
    public void multipleInstancesTest() {
        log.info("multipleInstancesTest");

        Map<GameInstanceServer, Future> instanceServers = Maps.newHashMap();

        for (int i = 0; i < configProperties.getGame().getCoreThreadCount() + 1; i++) {
            GameInstanceServer gameInstance = gameInstanceFactory.createGameInstance();
            instanceServers.put(gameInstance, taskExecutor.submit(gameInstance));
        }

        instanceServers.forEach((instance, future) -> {
            try {
                future.get();
                checkBattleRecordExist(instance);
            } catch (InterruptedException e) {
                log.error("Instance interrupted", e);
                assertTrue(true);
            } catch (ExecutionException e) {
                log.error("Instance execution error", e);
                assertTrue(true);
            }
        });
    }


}
