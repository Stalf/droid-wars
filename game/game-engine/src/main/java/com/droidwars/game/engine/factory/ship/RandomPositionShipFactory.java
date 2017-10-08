package com.droidwars.game.engine.factory.ship;

import com.droidwars.game.GameInstance;
import com.droidwars.game.engine.utils.GameUtils;
import com.droidwars.game.objects.ships.Frigate;
import com.droidwars.game.objects.ships.Ship;
import com.droidwars.game.objects.ships.ShipType;

public class RandomPositionShipFactory extends AbstractShipFactory {

    public RandomPositionShipFactory(GameInstance gameInstance) {
        super(gameInstance);
    }

    public Ship getShip(ShipType type, int teamNumber) {
        switch (type) {
            case FRIGATE: {
                return new Frigate(getGameInstance(), GameUtils.generateRandomPosition(), GameUtils.generateRandomDirection(), teamNumber);
            }
            default: {
                throw new UnsupportedOperationException(String.format("Ship type %s is not supported", type));
            }
        }
    }
}
