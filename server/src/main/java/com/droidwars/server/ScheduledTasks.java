package com.droidwars.server;

import com.droidwars.game.engine.GameInstanceServer;
import com.droidwars.game.engine.factory.game.AbstractGameInstanceFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ScheduledTasks {

    @Autowired
    private ConfigProperties configProperties;

    @Autowired
    private AbstractGameInstanceFactory gameInstanceFactory;

    @Autowired
    private TaskExecutor taskExecutor;

    @Bean
    @Lazy
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(configProperties.getGame().getCoreThreadCount());
        pool.setMaxPoolSize(configProperties.getGame().getMaxThreadCount());
        pool.setQueueCapacity(configProperties.getGame().getMaxQueueSize());

        pool.setAwaitTerminationSeconds(configProperties.getGame().getAwaitTerminationSeconds());
        pool.setWaitForTasksToCompleteOnShutdown(false);

        return pool;
    }

    /**
     * Periodically reads battle calculation requests ans starts their execution
     */
    @Scheduled(initialDelay = 10_000, fixedDelay = 10_000)
    public void checkNewBattles() {
        log.debug("Start check new battles task");

        GameInstanceServer gameInstance = gameInstanceFactory.createGameInstance();

        taskExecutor.execute(gameInstance);

    }

}
