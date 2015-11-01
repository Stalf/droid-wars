package com.droidwars.game.objects.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.GameInstance;

/**
 * Ракетный класс снарядов
 */
public class Missile extends Projectile {

    public Missile(GameInstance gameInstance, Vector2 position, Vector2 facing, Vector2 startingVelocity, float damage, float maxDistance, float accel) {
        super(gameInstance, position, facing);

        this.setAcceleration(accel);
        this.setDamage(damage);
        this.setMaxDistance(maxDistance);
        this.setVelocity(startingVelocity);

    }

}
