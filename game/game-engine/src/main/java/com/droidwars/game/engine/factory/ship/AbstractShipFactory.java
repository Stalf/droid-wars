package com.droidwars.game.engine.factory.ship;

import com.droidwars.game.GameInstance;
import com.droidwars.game.factory.GameObjectFactory;
import com.droidwars.game.objects.ships.Ship;
import com.droidwars.game.objects.ships.ShipType;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Абстрактная фабрика кораблей
 */
@RequiredArgsConstructor
public abstract class AbstractShipFactory implements GameObjectFactory{

    @NonNull
    @Getter
    protected GameInstance gameInstance;

    /**
     * Генерирует корабль
     * @param type запрошенный тип корабля
     * @return новый корабль
     */
    public abstract Ship getShip(ShipType type, int teamNumber);

}
