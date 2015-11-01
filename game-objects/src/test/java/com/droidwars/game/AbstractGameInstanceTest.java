package com.droidwars.game;

import com.droidwars.game.record.GameRecordWriter;
import org.testng.annotations.BeforeClass;

public class AbstractGameInstanceTest {

    protected GameRecordWriter gameRecordWriter = new GameRecordWriterNullTestImpl();
    protected GameInstance gameInstance;

    @BeforeClass
    public void setupGameInstance() {
        gameInstance = new GameInstanceNullTestImpl(gameRecordWriter);
    }

}
