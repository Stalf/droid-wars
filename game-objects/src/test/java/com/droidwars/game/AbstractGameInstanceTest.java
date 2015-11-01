package com.droidwars.game;

import com.droidwars.game.record.GameRecordWriter;
import com.droidwars.game.record.GameRecordWriterNullImpl;
import org.testng.annotations.BeforeClass;

public class AbstractGameInstanceTest {

    protected GameRecordWriter gameRecordWriter = new GameRecordWriterNullImpl();
    protected GameInstance gameInstance;

    @BeforeClass
    public void setupGameInstance() {
        gameInstance = new GameInstanceNullTestImpl(gameRecordWriter);
    }

}
