package com.droidwars.game.record;

import lombok.Getter;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Meta information holder for a battle record - date, fighting sides, result, map configuration and so on
 */
@Getter
public class BattleRecordMetadata implements Serializable{

    /**
     * Unique battle identifier
     */
    private UUID battleId;

    /**
     *  When a server began execution of the battle
     */
    private LocalDateTime battleExecutionBegin;

    /**
     *  When a server finished battle execution
     */
    private LocalDateTime battleExecutionEnd;

    private ByteArrayOutputStream outputStream;

    private BattleRecordMetadata(LocalDateTime datetime) {
        this.battleExecutionBegin = datetime;
    }

    public static BattleRecordMetadata newInstance() {
        return new BattleRecordMetadata(LocalDateTime.now());
    }

    /**
     * Stores outputStream and fixes battle execution end time
     * @param outputStream stream containing recorded battle data
     */
    public void store(ByteArrayOutputStream outputStream) {
        this.outputStream = outputStream;
        this.battleExecutionEnd = LocalDateTime.now();
    }
}
