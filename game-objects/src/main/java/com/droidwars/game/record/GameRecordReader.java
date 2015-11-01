package com.droidwars.game.record;

import java.io.InputStream;

/**
 * Интерфейс воспроизведения хода боя
 */
public interface GameRecordReader {

    BattleRecord read(InputStream inputStream);

}
