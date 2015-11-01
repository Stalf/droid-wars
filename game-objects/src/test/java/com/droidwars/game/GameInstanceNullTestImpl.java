package com.droidwars.game;

import com.droidwars.game.generator.IdGenerator;
import com.droidwars.game.generator.SimpleIdGenerator;
import com.droidwars.game.record.GameRecordWriter;

public class GameInstanceNullTestImpl implements GameInstance {

    private final SimpleIdGenerator idGenerator = new SimpleIdGenerator();

    private final GameRecordWriter gameRecordWriter;

    public GameInstanceNullTestImpl(GameRecordWriter gameRecordWriter) {
        this.gameRecordWriter = gameRecordWriter;
    }

    @Override
    public IdGenerator getIdGenerator() {
        return idGenerator;
    }

    @Override
    public GameRecordWriter getGameRecordWriter() {
        return gameRecordWriter;
    }
}
