package com.droidwars.game.record.pojo;

import com.droidwars.game.command.Command;
import com.droidwars.game.exceptions.GameException;
import com.droidwars.game.objects.GameObject;
import com.droidwars.game.objects.ships.Ship;
import com.droidwars.game.record.BattleRecordMetadata;
import com.droidwars.game.record.RecordWriter;
import com.droidwars.game.record.StepRecord;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Implementation of battle writer based on POJO
 * Suboptimal (slow and memory-eager), needs refactoring!
 */
@Slf4j
public class POJORecordWriterImpl implements RecordWriter {

    private BattleRecord battleRecord;
    private BattleRecordMetadata recordMetadata;
    private StepRecord tmpStep = null;
    private float time = 0f;

    @Override
    public void startRecord(BattleRecordMetadata recordMetadata, List<Ship> ships) {

        if (log.isTraceEnabled()) {
            log.trace("Start battle recording. Ships: {}", ships);
        }

        this.recordMetadata = recordMetadata;
        this.battleRecord = new BattleRecord(ships);
    }

    @Override
    public void write(GameObject subject, Command<Ship> command) {
        if (tmpStep == null) {
            throw new IllegalStateException("write called before stepBegin");
        }

        tmpStep.record(subject, command);
    }

    @Override
    public void stepBegin(float delta) {
        if (recordMetadata == null) {
            throw new IllegalStateException("stepBegin called before startRecord");
        }
        if (tmpStep != null) {
            throw new IllegalStateException("stepBegin called twice without stepEnd");
        }
        time += delta;
        tmpStep = new StepRecord(delta);
    }

    @Override
    public void stepEnd() {
        if (tmpStep == null) {
            throw new IllegalStateException("stepEnd called before stepBegin");
        }
        battleRecord.add(tmpStep);
        tmpStep = null;
    }

    @Override
    public BattleRecordMetadata stopRecord() throws GameException {
        log.trace("Stop battle recording");

        try {

            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(recordMetadata.store())) {
                objectOutputStream.writeObject(battleRecord);
                objectOutputStream.flush();
            }
            return recordMetadata;

        } catch (IOException e) {
            throw new GameException("Error writing battleRecord to a stream", e);
        } finally {
            battleRecord = null;
            tmpStep = null;
        }

    }

}
