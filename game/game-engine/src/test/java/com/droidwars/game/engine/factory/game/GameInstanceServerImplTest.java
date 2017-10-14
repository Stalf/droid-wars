package com.droidwars.game.engine.factory.game;

import com.droidwars.game.engine.factory.ship.AbstractShipFactory;
import com.droidwars.game.exceptions.GameException;
import com.droidwars.game.generator.IdGenerator;
import com.droidwars.game.objects.ships.Ship;
import com.droidwars.game.objects.ships.ShipType;
import com.droidwars.game.record.BattleRecordMetadata;
import com.droidwars.game.record.RecordStorage;
import com.droidwars.game.record.RecordWriter;
import lombok.extern.slf4j.Slf4j;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;

import static com.droidwars.game.engine.utils.Constants.BATTLE_TIMEOUT;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertNotNull;

@Slf4j
public class GameInstanceServerImplTest {

    @Mock
    private IdGenerator idGeneratorMock;
    @Mock
    private RecordWriter recordWriterMock;
    @Mock
    private RecordStorage recordStorageMock;
    @Mock
    private AbstractShipFactory abstractShipFactory;

    @InjectMocks
    private GameInstanceServerImpl gameInstanceServer;

    private InOrder recordWriterMockInOrder;

    @BeforeMethod
    public void setUp() throws GameException {
        MockitoAnnotations.initMocks(this);
        gameInstanceServer.setShipFactory(abstractShipFactory);

        recordWriterMockInOrder = inOrder(recordWriterMock);

        doReturn(mock(Ship.class)).when(abstractShipFactory).getShip(ShipType.FRIGATE, 1);
        doReturn(mock(Ship.class)).when(abstractShipFactory).getShip(ShipType.FRIGATE, 2);
        BattleRecordMetadata recordMetadataMock = mock(BattleRecordMetadata.class);
        when(recordStorageMock.createMetadata()).thenReturn(recordMetadataMock);
        when(recordMetadataMock.getBattleId()).thenReturn(UUID.randomUUID());

        assertNotNull(gameInstanceServer.getRecordWriter());
    }

    @Test
    public void gameTest1() throws GameException {

        gameInstanceServer.run();

        Assert.assertEquals(gameInstanceServer.getTime(), BATTLE_TIMEOUT, 0.01f);

        verify(recordStorageMock, times(1)).createMetadata();
        verify(recordStorageMock, times(1)).store(any());

        verify(recordWriterMock, times(1)).startRecord(any(), any());
        verify(recordWriterMock, times(1)).stopRecord();
        verify(recordWriterMock, atLeast(1)).stepBegin(anyFloat());
        verify(recordWriterMock, atLeast(1)).stepEnd();

        recordWriterMockInOrder.verify(recordWriterMock).startRecord(any(), any());
        recordWriterMockInOrder.verify(recordWriterMock).stopRecord();

    }

}
