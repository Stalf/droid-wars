package com.droidwars.game.record.pojo;

import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.GameInstance;
import com.droidwars.game.GameInstanceNullTestImpl;
import com.droidwars.game.TestConstants;
import com.droidwars.game.command.ship.TurnDirectionCommand;
import com.droidwars.game.objects.ships.Ship;
import com.droidwars.game.record.BattleRecordMetadata;
import com.droidwars.game.record.GameRecordReader;
import com.droidwars.game.record.GameRecordWriter;
import com.droidwars.game.record.StepRecord;
import com.droidwars.game.record.pojo.POJOGameRecordWriterImpl;
import com.google.common.collect.Lists;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class POJORecordeImplTest {

    private GameRecordWriter gameRecordWriter;
    private Ship ship;

    @BeforeMethod
    public void setup() {
        gameRecordWriter =  new POJOGameRecordWriterImpl();
        GameInstance gameInstance = new GameInstanceNullTestImpl(gameRecordWriter);
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


        BattleRecordMetadata recordMetadata = gameRecordWriter.stopRecord();
        Assert.assertNotNull(recordMetadata);

        ByteArrayOutputStream outputStream = recordMetadata.getOutputStream();
        Assert.assertTrue(outputStream.size() > 0);

        GameRecordReader gameRecordReader = new POJOGameRecordReaderImpl(new ByteArrayInputStream(outputStream.toByteArray()));

        Assert.assertEquals(gameRecordReader.getShips().size(), 1);

        StepRecord stepRecord = gameRecordReader.peek();
        Assert.assertNotNull(stepRecord);
        Assert.assertEquals(stepRecord.getShipCommands().size(), 1);
        Assert.assertEquals(stepRecord.getDelta(), TestConstants.DELTA_STEP);

        Assert.assertNotNull(gameRecordReader.poll());
        Assert.assertNotNull(gameRecordReader.poll());
        Assert.assertNull(gameRecordReader.poll());

    }

}
