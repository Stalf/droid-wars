package com.droidwars.game.weaponry;

import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.factory.GameObjectFactory;
import com.droidwars.game.generator.IdGenerator;
import com.droidwars.game.objects.projectiles.Projectile;
import com.droidwars.game.objects.ships.Ship;
import lombok.AccessLevel;
import lombok.Getter;

/**
 * Абстрактное орудие. Является фабрикой для {@link Projectile}
 */
public abstract class AbstractWeapon implements Weapon, GameObjectFactory {

    private IdGenerator idGenerator;

    /**
     * Корабль, на котором установлено орудие
     */
    @Getter(AccessLevel.PROTECTED)
    private Ship ship;

    public AbstractWeapon(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public abstract Projectile shoot();

    /**
     * @return координаты начальной точки снаряда
     */
    public abstract Vector2 getShootingPosition();

    @Override
    public long generateId() {
        return idGenerator.getNextId();
    }
}
