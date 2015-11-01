package com.droidwars.game.objects.ships;

import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.GameInstance;
import com.droidwars.game.weaponry.MissileLauncher;
import com.droidwars.game.weaponry.Weapon;
import com.google.common.collect.ImmutableList;

public class Frigate extends Ship {

    public Frigate(GameInstance gameInstance, Vector2 position, Vector2 facing) {
        super(gameInstance, position, facing);

        setWeaponSlots(ImmutableList.<Weapon>of(new MissileLauncher(gameInstance)).asList());

    }

    @Override
    public void shoot() {

    }

    @Override
    public void shoot(int slot) {
        this.shoot();
    }
}
