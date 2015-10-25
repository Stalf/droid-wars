package com.droidwars.game.engine.utils;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * Вспомогательный класс с утилитами
 */
public class GameUtils {

    /**
     * Генерирует случайный нормализованный вектор направления
     *
     * @return случайный вектор направления
     */
    public static Vector2 generateRandomDirection() {
        return new Vector2(MathUtils.random(-1f, 1f), MathUtils.random(-1f, 1f)).nor();
    }

    /**
     * Генерирует случайный вектор положения объекта в игровом мире
     *
     * @return случайный вектор положения в мире
     */
    public static Vector2 generateRandomPosition() {
        return new Vector2(MathUtils.random(0, Constants.MAP_WIDTH), MathUtils.random(0, Constants.MAP_HEIGHT));
    }

}
