package com.droidwars.game.objects;

import com.droidwars.game.exceptions.GameException;

/**
 * Основные методы игрового объекта
 */
public interface GameObject {

    /**
     * @return признак существования объекта в игровом мире
     */
    boolean isAlive();

    /**
     * Обновляет состояние объекта
     * @param delta - время, прошедшее с предыдущего такта
     */
    void update(float delta) throws GameException;

    /**
     * Уничтожает объект
     */
    void destroy();
}
