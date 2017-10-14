package com.droidwars.game;

import com.droidwars.game.generator.IdGenerator;
import com.droidwars.game.record.RecordWriter;
import org.testng.annotations.BeforeClass;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AbstractGameInstanceTest {

    protected GameInstance gameInstanceMock;

    @BeforeClass
    public void setupGameInstance() {
        gameInstanceMock = mock(GameInstance.class);
        IdGenerator idGeneratorMock = mock(IdGenerator.class);
        when(gameInstanceMock.getIdGenerator()).thenReturn(idGeneratorMock);
        when(idGeneratorMock.getNextId()).thenReturn(100L);
        RecordWriter recordWriterMock = mock(RecordWriter.class);
        when(gameInstanceMock.getRecordWriter()).thenReturn(recordWriterMock);
    }

}
