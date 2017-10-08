package com.droidwars.game.objects;

import com.droidwars.game.GameInstance;
import com.droidwars.game.exceptions.GameException;

/**
 * Interface contains common game object methods
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

    /**
     * @return контроллер боя
     */
    GameInstance getGameInstance();

    /**
     * @return inner game object unique identifier
     */
    long getGameId();
}
