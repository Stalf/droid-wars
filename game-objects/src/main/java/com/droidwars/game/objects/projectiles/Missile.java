package com.droidwars.game.objects.projectiles;

import com.badlogic.gdx.math.Vector2;

/**
 * Ракетный класс снарядов
 */
public class Missile extends Projectile {

    public Missile(long id, Vector2 position, Vector2 facing, Vector2 startingVelocity, float damage, float maxDistance) {
        super(id, position, facing);

        this.setDamage(damage);
        this.setMaxDistance(maxDistance);
        this.velocity(startingVelocity);

    }

}
