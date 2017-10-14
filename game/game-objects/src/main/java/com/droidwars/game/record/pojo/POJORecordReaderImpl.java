package com.droidwars.game.record.pojo;

import com.droidwars.game.record.RecordReader;
import com.droidwars.game.record.ShipRecord;
import com.droidwars.game.record.StepRecord;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Map;


/**
 * Implementation of battle reader based on POJO
 * Suboptimal (slow and memory-eager), needs refactoring!
 */
@Slf4j
public class POJORecordReaderImpl implements RecordReader {

    private BattleRecord battleRecord;

    public POJORecordReaderImpl(InputStream inputStream) {

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            battleRecord = (BattleRecord) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            log.error("Error reading battle data from stream", e);
        }

    }

    @Override
    public Map<Long, ShipRecord> getShips() {
        return battleRecord.getShips();
    }

    @Override
    public StepRecord peek() {
        return battleRecord.getRecordList().peek();
    }

    @Override
    public StepRecord poll() {
        return battleRecord.getRecordList().poll();
    }
}
