package com.droidwars.game.record;

import lombok.extern.slf4j.Slf4j;
import org.hamcrest.io.FileMatchers;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.ClosedChannelException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.testng.Assert.assertTrue;

@Slf4j
public class BattleRecordMetadataFilesTests {

    private static final byte[] BYTES = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private Path tempFile;
    private OutputStream outputStream;

    @BeforeMethod
    public void setUpFiles() throws IOException {
        tempFile = Files.createTempFile("droidwars_test", "");
        outputStream = Files.newOutputStream(tempFile);
    }

    @AfterMethod
    public void cleanUp() {
        if (tempFile.toFile().exists()) {
            assertTrue(tempFile.toFile().delete());
        }
        assertThat(tempFile.toFile(), not(FileMatchers.anExistingFile()));
    }

    @Test
    public void testFileCreated() {
        assertThat(tempFile.toFile(), FileMatchers.anExistingFile());
    }

    @Test
    public void fileBasedStreamDataTest() throws IOException {
        BattleRecordMetadata recordMetadata = BattleRecordMetadata.newInstance(UUID.randomUUID(), outputStream);
        OutputStream outputStream2 = recordMetadata.store();
        outputStream2.write(BYTES);

        assertThat(tempFile.toFile(), FileMatchers.aFileWithSize(10));
    }


    @Test(expectedExceptions = {ClosedChannelException.class})
    public void closeableStreamTest() throws IOException {

        try (BattleRecordMetadata recordMetadata = BattleRecordMetadata.newInstance(UUID.randomUUID(), outputStream)) {
            recordMetadata.store().write(BYTES);
        }

        outputStream.write(BYTES);
    }


}
