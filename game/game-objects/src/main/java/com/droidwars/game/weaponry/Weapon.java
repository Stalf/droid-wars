package com.droidwars.game.weaponry;

import com.droidwars.game.objects.projectiles.Projectile;

/**
 * Интерфейс орудия
 */
public interface Weapon {

    /**
     * Производит выстрел
     * @return объект типа снаряд
     */
    Projectile shoot();

}
