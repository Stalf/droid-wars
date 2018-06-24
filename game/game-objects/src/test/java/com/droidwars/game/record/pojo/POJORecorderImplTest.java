package com.droidwars.game.record.pojo;

import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.GameInstance;
import com.droidwars.game.TestConstants;
import com.droidwars.game.command.ship.TurnDirectionCommand;
import com.droidwars.game.exceptions.GameException;
import com.droidwars.game.generator.IdGenerator;
import com.droidwars.game.objects.ships.Ship;
import com.droidwars.game.objects.ships.TestShip;
import com.droidwars.game.record.BattleRecordMetadata;
import com.droidwars.game.record.RecordReader;
import com.droidwars.game.record.RecordStorage;
import com.droidwars.game.record.RecordWriter;
import com.droidwars.game.record.StepRecord;
import com.google.common.collect.Lists;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class POJORecorderImplTest {

    private RecordWriter recordWriter;
    private Ship ship;
    private ByteArrayOutputStream mockOutputStream;
    private GameInstance gameInstanceMock;
    private RecordStorage mockRecordStorage;

    @BeforeMethod
    public void setup() throws GameException {
        recordWriter = new POJORecordWriterImpl();

        gameInstanceMock = mock(GameInstance.class);
        IdGenerator idGeneratorMock = mock(IdGenerator.class);
        when(gameInstanceMock.getIdGenerator()).thenReturn(idGeneratorMock);
        when(idGeneratorMock.getNextId()).thenReturn(100L);
        when(gameInstanceMock.getRecordWriter()).thenReturn(recordWriter);

        ship = new TestShip(gameInstanceMock, new Vector2(0, 0), new Vector2(1, 0), 1);

        mockRecordStorage = mock(RecordStorage.class);
        BattleRecordMetadata mockRecordMetadata = mock(BattleRecordMetadata.class);
        when(mockRecordStorage.createMetadata()).thenReturn(mockRecordMetadata);
        when(mockRecordMetadata.getBattleId()).thenReturn(UUID.randomUUID());
        mockOutputStream = new ByteArrayOutputStream();
        when(mockRecordMetadata.store()).thenReturn(mockOutputStream);
    }

    @Test
    public void testWriteAndRead() throws GameException {

        recordWriter.startRecord(mockRecordStorage.createMetadata(), Lists.newArrayList(ship));

        recordWriter.stepBegin(TestConstants.DELTA_STEP);

        ship.command(new TurnDirectionCommand(1));
        ship.update(TestConstants.DELTA_STEP);

        recordWriter.stepEnd();

        recordWriter.stepBegin(TestConstants.DELTA_STEP);

        ship.command(new TurnDirectionCommand(-1));
        ship.update(TestConstants.DELTA_STEP);

        recordWriter.stepEnd();

        BattleRecordMetadata recordMetadata = recordWriter.stopRecord();
        Assert.assertNotNull(recordMetadata);

        assertEquals(recordMetadata.store(), mockOutputStream);
        Assert.assertTrue(mockOutputStream.size() > 0);

        RecordReader recordReader = new POJORecordReaderImpl(new ByteArrayInputStream(mockOutputStream.toByteArray()));

        assertEquals(recordReader.getShips().size(), 1);

        StepRecord stepRecord = recordReader.peek();
        Assert.assertNotNull(stepRecord);
        assertEquals(stepRecord.getShipCommands().size(), 1);
        assertEquals(stepRecord.getDelta(), TestConstants.DELTA_STEP);

        Assert.assertNotNull(recordReader.poll());
        Assert.assertNotNull(recordReader.poll());
        Assert.assertNull(recordReader.poll());

    }

}
