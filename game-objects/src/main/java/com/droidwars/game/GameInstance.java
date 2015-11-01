package com.droidwars.game;

import com.droidwars.game.generator.IdGenerator;
import com.droidwars.game.record.GameRecordWriter;

/**
 * Интерфейс экземпляра контроллера боя
 */
public interface GameInstance {

    /**
     * @return экземпляр генератора уникальных идентификаторов
     */
    IdGenerator getIdGenerator();

    /**
     * @return экземпляр объекта для записи хода боя
     */
    GameRecordWriter getGameRecordWriter();
}
