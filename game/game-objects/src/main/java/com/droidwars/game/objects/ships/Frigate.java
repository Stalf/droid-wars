package com.droidwars.game.objects.ships;

import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.GameInstance;
import com.droidwars.game.weaponry.MissileLauncher;

public class Frigate extends Ship {

    private static final int MAX_FRIGATE_HULL_POINTS = 2000;

    public Frigate(GameInstance gameInstance, Vector2 position, Vector2 facing, int teamNumber) {
        super(gameInstance, position, facing, teamNumber);

        setMaxHullPoints(MAX_FRIGATE_HULL_POINTS);
        setWeaponSlots(1);
        putWeapon(new MissileLauncher(gameInstance), 1);
    }

    @Override
    public void shoot() {

    }

    @Override
    public void shoot(int slot) {
        this.shoot();
    }
}
