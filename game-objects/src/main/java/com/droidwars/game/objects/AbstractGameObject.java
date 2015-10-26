package com.droidwars.game.objects;

import com.badlogic.gdx.math.Vector2;
import lombok.*;

/**
 * Абстрактный объект игрового мира
 */
@EqualsAndHashCode(of = "gameId")
@ToString
@Getter
@Setter(AccessLevel.PROTECTED)
public abstract class AbstractGameObject implements GameObject {
    /**
     * Время жизни
     */
    private float aliveTime = 0.0f;

    /**
     * Пройденное расстояние
     */
    private float travelDistance = 0.0f;

    /**
     * Координаты центра объекта
     */
    private Vector2 position = new Vector2();

    /**
     * Направление, в котором повернут объект
     */
    private Vector2 facing = new Vector2();

    /**
     * Скорость - м / с
     */
    private Vector2 velocity = new Vector2();

    /**
     * Ускорение - м / с^2; <br/>
     * Вектор ускорения применяется в направлении {@link AbstractGameObject#facing}
     */
    private float acceleration = 10.0f;

    /**
     * Ширина объекта в метрах (отсчитывается по оси X)
     */
    private int width = 50;

    /**
     * Высота объекта в метрах (отсчитывается по оси Y)
     */
    private int height = 50;

    /**
     * Признак существования объекта в игровом мире
     */
    @Setter(AccessLevel.NONE)
    private boolean alive = true;

    /**
     * Внутрениий игровой идентификатор объекта
     */
    private long gameId = 0;

    /**
     * Время, прошедшее с предыдущего такта. Значение параметра обновляется на каждом такте
     */
    protected float delta = 0f;

    public AbstractGameObject(long id, Vector2 position, Vector2 facing) {
        this.gameId = id;

        this.position.set(position);
        this.facing.set(facing.nor());

    }

    @Override
    public void update(float delta) {
        this.delta = delta;

        if (!alive) {
            return;
        }

        this.aliveTime += delta;

        // Изменяем положение под воздействием скорости
        this.position.add(velocity.x * delta, velocity.y * delta);
        this.travelDistance += velocity.len() * delta;
    }

    @Override
    public void destroy() {
        this.alive = false;
    }
}
