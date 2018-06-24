package com.droidwars.game.objects.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.GameInstance;

public class TestProjectile extends Projectile {
    public TestProjectile(GameInstance gameInstance, Vector2 position, Vector2 facing) {
        super(gameInstance, position, facing);
    }

    public TestProjectile(GameInstance gameInstance, Vector2 position, Vector2 facing, Vector2 velocity) {
        super(gameInstance, position, facing);
        this.setVelocity(velocity);
    }

    public TestProjectile(GameInstance gameInstance, Vector2 position, Vector2 facing, Vector2 velocity, float acceleration) {
        super(gameInstance, position, facing);
        this.setVelocity(velocity);
        this.setAcceleration(acceleration);
    }
}
