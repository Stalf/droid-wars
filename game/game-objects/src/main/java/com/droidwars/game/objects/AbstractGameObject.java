package com.droidwars.game.objects;

import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.GameInstance;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Abstract game object
 */
@EqualsAndHashCode(of = "gameId")
@ToString
@Getter
@Setter(AccessLevel.PROTECTED)
public abstract class AbstractGameObject implements GameObject {
    /**
     * Time that object was (or is) alive in game
     */
    private float aliveTime = 0.0f;

    /**
     * Distance travelled by the object
     */
    private float travelDistance = 0.0f;

    /**
     * Coordinates of the object's logical center
     */
    private Vector2 position = new Vector2();

    /**
     * Direction in which the object is faced
     */
    private Vector2 facing = new Vector2();

    /**
     * Velocity in m/s
     */
    private Vector2 velocity = new Vector2();

    /**
     * Acceleration - m / s^2; <br/>
     * Acceleration vector is applied in {@link AbstractGameObject#facing} direction
     */
    private float acceleration = 0.0f;

    /**
     * Object width in meters (counted by X axis)
     */
    private int width = 0;

    /**
     * Object height in meters (counted by Y axis)
     */
    private int height = 0;

    /**
     * Property stating if object is alive or not
     */
    @Setter(AccessLevel.NONE)
    private boolean alive = true;

    /**
     * Ingame object identifier
     */
    private long gameId = 0;

    /**
     * Reference to a game instance controller
     */
    private GameInstance gameInstance;

    /**
     * Game time since last tick. This value is updated in every tick
     */
    protected float delta = 0f;

    protected AbstractGameObject(GameInstance gameInstance, Vector2 position, Vector2 facing) {
        this.gameInstance = gameInstance;
        this.gameId = gameInstance.getIdGenerator().getNextId();

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

        // Change position regarding the velocity
        Vector2 deltaVector = new Vector2(velocity.x * delta, velocity.y * delta);
        this.position.add(deltaVector);
        this.travelDistance += deltaVector.len();

    }

    @Override
    public void destroy() {
        this.alive = false;
    }
}
