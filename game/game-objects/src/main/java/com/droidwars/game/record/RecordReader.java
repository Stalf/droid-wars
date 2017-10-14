package com.droidwars.game.record;

import java.util.Map;

/**
 * Интерфейс воспроизведения хода боя
 */
public interface RecordReader {

    Map<Long, ShipRecord> getShips();

    StepRecord peek();

    StepRecord poll();
}
