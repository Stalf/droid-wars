package com.droidwars.game.objects.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.GameInstance;
import com.droidwars.game.objects.AbstractGameObject;
import com.droidwars.game.objects.ships.Ship;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Abstract class for projectile that can cause damage
 */
public abstract class Projectile extends AbstractGameObject {

    /**
     * Damage value
     */
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private float damage = 0;

    /**
     * Maximum projectile life time (0 - unlimited)
     */
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private float maxAliveTime = 0;

    /**
     * Maximum projectile travel distance (0 - unlimited)
     */
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private float maxDistance = 0;

    /**
     * Ship object, that fired this projectile
     */
    @Getter(AccessLevel.PACKAGE)
    @Setter(AccessLevel.PROTECTED)
    private Ship owner;

    public Projectile(GameInstance gameInstance, Vector2 position, Vector2 facing) {
        super(gameInstance, position, facing);
    }

    @Override
    public void update(float delta) {
        if (!isAlive()) {
            return;
        }

        super.update(delta);

        // Increase velocity
        this.getVelocity().add(getFacing().x * getAcceleration() * delta, getFacing().y * getAcceleration() * delta);

        // Check distance and time to live conditions
        if ((this.maxAliveTime > 0) && (this.getAliveTime() > this.maxAliveTime)) {
            this.destroy();
        }
        if ((this.maxDistance > 0) && (this.getTravelDistance() > this.maxDistance)) {
            this.destroy();
        }

    }

}
