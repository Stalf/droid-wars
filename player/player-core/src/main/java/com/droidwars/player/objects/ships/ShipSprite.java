package com.droidwars.player.objects.ships;

import com.droidwars.game.objects.ships.Ship;
import com.droidwars.player.objects.GameObjectSprite;

/**
 * Графическое представление абстрактного корабля
 */
public class ShipSprite extends GameObjectSprite<Ship> {

    public ShipSprite(Ship subject) {
        super(subject);
    }

    public String getHealth() {
        return String.format("HP: %.0f / %.0f", this.subject.getHullPoints(), this.subject.getMaxHullPoints());
    }
}
