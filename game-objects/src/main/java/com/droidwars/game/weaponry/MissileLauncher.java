package com.droidwars.game.weaponry;

import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.generator.IdGenerator;
import com.droidwars.game.objects.projectiles.Missile;
import com.droidwars.game.objects.projectiles.Projectile;
import lombok.NonNull;

/**
 * Ракетная пусковая установка
 */
public class MissileLauncher extends AbstractWeapon {

    private static final float initialMissileVelocity = 150;
    private static final float missileDamage = 100;
    private static final float missileMaxDistance = 700;
    public static final int missile_acceleration = 10;

    public MissileLauncher(@NonNull IdGenerator idGenerator) {
        super(idGenerator);
    }

    @Override
    public Projectile shoot() {

        Vector2 missileVelocity = new Vector2(getShip().getFacing()).scl(initialMissileVelocity).add(getShip().getVelocity());

        return new Missile(generateId(), getShootingPosition(), getShip().getFacing(), missileVelocity, missileDamage, missileMaxDistance, missile_acceleration);
    }

    @Override
    public Vector2 getShootingPosition() {
        // TODO реализовать реальное расположение орудий на кораблях
        return getShip().getPosition();
    }
}
