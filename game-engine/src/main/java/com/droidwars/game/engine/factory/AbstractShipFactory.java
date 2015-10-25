package com.droidwars.game.engine.factory;

import com.droidwars.game.factory.GameObjectFactory;
import com.droidwars.game.generator.IdGenerator;
import com.droidwars.game.objects.ships.Ship;
import com.droidwars.game.objects.ships.ShipType;
import lombok.NonNull;

/**
 * Абстрактная фабрика кораблей
 */
public abstract class AbstractShipFactory implements GameObjectFactory{

    protected IdGenerator idGenerator;

    public AbstractShipFactory(@NonNull IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    /**
     * Генерирует корабль
     * @param type запрошенный тип корабля
     * @return новый корабль
     */
    public abstract Ship getShip(ShipType type);

    @Override
    public long generateId() {
        return idGenerator.getNextId();
    }
}
