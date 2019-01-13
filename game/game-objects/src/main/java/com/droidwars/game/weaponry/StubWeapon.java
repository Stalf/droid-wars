package com.droidwars.game.weaponry;

import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.GameInstance;
import com.droidwars.game.objects.projectiles.Projectile;

public class StubWeapon extends AbstractWeapon {

    public StubWeapon(GameInstance gameInstance) {
        super(gameInstance);
    }

    @Override
    public Vector2 getShootingPosition() {
        return Vector2.Zero;
    }

    @Override
    public Projectile shoot() {
        return null;
    }
}
