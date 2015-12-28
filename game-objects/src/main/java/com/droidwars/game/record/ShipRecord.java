package com.droidwars.game.record;

import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.objects.ships.Ship;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class ShipRecord implements Serializable {

    private Class<? extends Ship> shipClass;
    private int teamNumber;
    private Vector2 position;
    private Vector2 facing;

}

