package com.droidwars.game.record;

import org.exparity.hamcrest.date.LocalDateTimeMatchers;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class BattleRecordMetadataTests {

    private static final byte[] BYTES = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    @Test
    public void simpleTest() {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BattleRecordMetadata recordMetadata = BattleRecordMetadata.newInstance(UUID.randomUUID(), outputStream);
        OutputStream outputStream2 = recordMetadata.store();

        assertEquals(outputStream, outputStream2);
        assertThat(recordMetadata.getBattleExecutionBegin(), LocalDateTimeMatchers.sameOrBefore(LocalDateTime.now()));
        assertThat(recordMetadata.getBattleExecutionBegin(), LocalDateTimeMatchers.sameOrBefore(recordMetadata.getBattleExecutionEnd()));

    }

    @Test
    public void byteArrayStreamDataTest() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BattleRecordMetadata recordMetadata = BattleRecordMetadata.newInstance(UUID.randomUUID(), outputStream);
        OutputStream outputStream2 = recordMetadata.store();

        outputStream2.write(BYTES);

        assertEquals(outputStream.toByteArray(), BYTES);

    }

}
