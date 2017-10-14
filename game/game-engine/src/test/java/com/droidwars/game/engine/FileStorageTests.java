package com.droidwars.game.engine;

import com.droidwars.game.exceptions.GameException;
import com.droidwars.game.record.BattleRecordMetadata;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.hamcrest.io.FileMatchers;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Slf4j
public class FileStorageTests {

    private static final byte[] BYTES = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private static final String GUID_DAT = "%s.dat";

    private Path tempDir;
    private FileStorage fileStorage;

    @BeforeClass
    public void setUp() throws IOException {
        tempDir = Files.createTempDirectory("droidwars_test");
        log.debug("working dir = {}", tempDir.toString());
        assertThat(tempDir.toFile(), FileMatchers.anExistingDirectory());
    }

    @AfterClass
    public void cleanUp() throws IOException {
        if (tempDir.toFile().exists()) {
            FileUtils.cleanDirectory(tempDir.toFile());
            assertTrue(tempDir.toFile().delete());
        }
    }

    @BeforeMethod
    public void beforeMethod() {
        fileStorage = new FileStorage(tempDir);
    }

    @Test
    public void simpleTest() throws GameException, IOException {

        try (BattleRecordMetadata metadata = fileStorage.createMetadata()) {
            Path path = Paths.get(tempDir.toString(), String.format("%s.dat", metadata.getBattleId().toString()));
            assertThat(path.toFile(), FileMatchers.anExistingFile());
            assertThat(path.toFile(), FileMatchers.aWritableFile());
        }
    }

    @Test
    public void writeFileTest() throws GameException, IOException {

        try (BattleRecordMetadata metadata = fileStorage.createMetadata()) {
            Path path = Paths.get(tempDir.toString(), String.format("%s.dat", metadata.getBattleId().toString()));
            metadata.store().write(BYTES);

            assertThat(path.toFile(), FileMatchers.aFileWithSize(10));
            assertEquals(BYTES, FileUtils.readFileToByteArray(path.toFile()));
        }
    }

    @Test
    public void writeLargePieceOfDataTest() throws GameException, IOException {
        Path path;

        try (BattleRecordMetadata metadata = fileStorage.createMetadata()) {
            path = Paths.get(tempDir.toString(), String.format(GUID_DAT, metadata.getBattleId().toString()));

            for (int i=0; i < 1_000_000; i++) {
                metadata.store().write(BYTES);
            }
            assertThat(path.toFile(), FileMatchers.aFileWithSize(10_000_000));
        }
    }

}
