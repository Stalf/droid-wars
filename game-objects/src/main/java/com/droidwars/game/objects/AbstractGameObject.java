package com.droidwars.game.objects;

import com.badlogic.gdx.math.Vector2;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * Абстрактный объект игрового мира
 */
@EqualsAndHashCode(of = "gameId")
@ToString
public abstract class AbstractGameObject implements GameObject {
    /**
     * Время жизни
     */
    @Getter
    private float aliveTime = 0.0f;

    /**
     * Пройденное расстояние
     */
    @Getter
    private float travelDistance = 0.0f;

    /**
     * Координаты центра объекта
     */
    @Accessors(fluent = true)
    @Getter(AccessLevel.PROTECTED)
    private Vector2 position = new Vector2();

    @Override
    public Vector2 getPosition() {
        return this.position.cpy();
    }

    /**
     * Направление, в котором повернут объект
     */
    @Accessors(fluent = true)
    @Getter(AccessLevel.PROTECTED)
    private Vector2 facing = new Vector2();

    @Override
    public Vector2 getFacing() {
        return this.facing.cpy();
    }

    /**
     * Скорость - м / с
     */
    @Accessors(fluent = true)
    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    private Vector2 velocity = new Vector2();

    @Override
    public Vector2 getVelocity() {
        return this.velocity.cpy();
    }

    /**
     * Ускорение - м / с^2; <br/>
     * Вектор ускорения применяется в направлении {@link AbstractGameObject#facing}
     */
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private float acceleration = 0.0f;

    /**
     * Ширина объекта в метрах (отсчитывается по оси X)
     */
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private int width = 0;

    /**
     * Высота объекта в метрах (отсчитывается по оси Y)
     */
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private int height = 0;

    /**
     * Признак существования объекта в игровом мире
     */
    @Getter
    private boolean alive = true;

    /**
     * Внутрениий игровой идентификатор объекта
     */
    @Getter
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
