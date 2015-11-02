package com.droidwars.game.record;

import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.GameInstance;
import com.droidwars.game.GameInstanceNullTestImpl;
import com.droidwars.game.TestConstants;
import com.droidwars.game.command.ship.TurnDirectionCommand;
import com.droidwars.game.objects.ships.Ship;
import com.google.common.collect.Lists;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class RecorderJavaSerializationImplTest {

    private GameRecordWriter gameRecordWriter;
    private GameRecordReader gameRecordReader;
    private GameInstance gameInstance;
    private Ship ship;

    @BeforeMethod
    public void setup() {
        gameRecordWriter =  new JavaSerializationGameRecordWriterImpl();
        gameRecordReader = new JavaSerializationGameRecordReaderImpl();
        gameInstance = new GameInstanceNullTestImpl(gameRecordWriter);
        ship = new Ship(gameInstance, new Vector2(0,0), new Vector2(1,0), 1);

        gameRecordWriter.startRecord(Lists.newArrayList(ship));
    }

    @Test
    public void testRecording() {

        gameRecordWriter.stepBegin(TestConstants.DELTA_STEP);

        ship.command(new TurnDirectionCommand(1));
        ship.update(TestConstants.DELTA_STEP);

        gameRecordWriter.stepEnd();

        gameRecordWriter.stepBegin(TestConstants.DELTA_STEP);

        ship.command(new TurnDirectionCommand(-1));
        ship.update(TestConstants.DELTA_STEP);

        gameRecordWriter.stepEnd();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Assert.assertTrue(gameRecordWriter.stopRecord(outputStream));
        Assert.assertTrue(outputStream.size() > 0);

        BattleRecord battleRecord = gameRecordReader.read(new ByteArrayInputStream(outputStream.toByteArray()));

        Assert.assertNotNull(battleRecord);
        Assert.assertEquals(battleRecord.getShips().size(), 1);
        Assert.assertEquals(battleRecord.getRecordList().size(), 2);

        StepRecord stepRecord = battleRecord.getRecordList().peek();
        Assert.assertNotNull(stepRecord);
        Assert.assertEquals(stepRecord.getShipCommands().size(), 1);
        Assert.assertEquals(stepRecord.getDelta(), TestConstants.DELTA_STEP);
    }

}
