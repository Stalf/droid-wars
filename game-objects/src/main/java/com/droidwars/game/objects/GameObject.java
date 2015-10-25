package com.droidwars.game.objects;

import com.badlogic.gdx.math.Vector2;

/**
 * Основные методы игрового объекта
 */
public interface GameObject {

    /**
     * Обновляет состояние объекта
     * @param delta - время, прошедшее с предыдущего такта
     */
    void update(float delta);

    /**
     * Уничтожает объект
     */
    void destroy();

    /**
     * Возвращает публичную копию вектора координат объекта
     * @return координаты центра объекта
     */
    Vector2 getPosition();

    /**
     * Возвращает публичную копию вектора направления объекта
     * @return направление, в котором повернут объект
     */
    Vector2 getFacing();

    /**
     * Возвращает публичную копию вектора скорости
     * @return скорость объекта - м /с
     */
    Vector2 getVelocity();
}
