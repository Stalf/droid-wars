package com.droidwars.game.objects.ships;

import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.generator.IdGenerator;
import com.droidwars.game.weaponry.MissileLauncher;
import com.droidwars.game.weaponry.Weapon;
import com.google.common.collect.ImmutableList;

public class Frigate extends Ship {

    public Frigate(long id, Vector2 position, Vector2 facing, IdGenerator idGenerator) {
        super(id, position, facing);

        weaponSlots = ImmutableList.<Weapon>of(new MissileLauncher(idGenerator)).asList();

    }

    @Override
    public void shoot() {

    }

    @Override
    public void shoot(int slot) {
        this.shoot();
    }
}
