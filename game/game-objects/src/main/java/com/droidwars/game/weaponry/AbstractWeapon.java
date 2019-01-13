package com.droidwars.game.weaponry;

import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.GameInstance;
import com.droidwars.game.factory.GameObjectFactory;
import com.droidwars.game.objects.projectiles.Projectile;
import com.droidwars.game.objects.ships.Ship;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Абстрактное орудие. Является фабрикой для {@link Projectile}
 */
@RequiredArgsConstructor
public abstract class AbstractWeapon implements Weapon, GameObjectFactory {

    @NonNull
    private GameInstance gameInstance;

    /**
     * Корабль, на котором установлено орудие
     */
    @Getter(AccessLevel.PROTECTED)
    private Ship ship;

    /**
     * @return координаты начальной точки снаряда
     */
    public abstract Vector2 getShootingPosition();

    @Override
    public GameInstance getGameInstance() {
        return gameInstance;
    }
}
