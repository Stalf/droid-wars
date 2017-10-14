package com.droidwars.game.record;

import lombok.Getter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Meta information holder for a battle record - date, fighting sides, result, map configuration and so on
 */
public class BattleRecordMetadata implements Serializable, AutoCloseable {

    /**
     * Unique battle identifier
     */
    @Getter
    private UUID battleId;

    /**
     * When a server began execution of the battle
     */
    @Getter
    private LocalDateTime battleExecutionBegin;

    /**
     * When a server finished battle execution
     */
    @Getter
    private LocalDateTime battleExecutionEnd;

    /**
     * Output stream representing underlying storage system
     */
    private OutputStream outputStream;

    private BattleRecordMetadata(UUID battleId, OutputStream outputStream, LocalDateTime datetime) {
        this.battleId = battleId;
        this.outputStream = outputStream;
        this.battleExecutionBegin = datetime;
    }

    public static BattleRecordMetadata newInstance(UUID battleId, OutputStream outputStream) {
        return new BattleRecordMetadata(battleId, outputStream, LocalDateTime.now());
    }

    /**
     * Returns outputStream and fixes battle execution end time
     */
    public OutputStream store() {
        this.battleExecutionEnd = LocalDateTime.now();
        return this.outputStream;
    }

    /**
     * Saves metadata and flushes inner outputStream
     */
    public void save() throws IOException {
        this.outputStream.flush();
    }

    @Override
    public void close() throws IOException {
        if (outputStream != null) {
            outputStream.close();
        }
    }
}
