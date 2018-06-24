package com.droidwars.game.record.pojo;

import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.GameInstance;
import com.droidwars.game.factory.GameObjectFactory;
import com.droidwars.game.objects.ships.FantomShip;
import com.droidwars.game.objects.ships.Ship;
import com.droidwars.game.record.ShipRecord;
import com.google.common.collect.Maps;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Factory creates ships in a state they were in the battle record
 */
@Slf4j
@RequiredArgsConstructor
public class BattleRecordShipFactory implements GameObjectFactory {

    @NonNull
    private GameInstance gameInstance;

    /**
     * @param shipRecordMap battle record with raw ships data
     * @return Map with ships, gameId as a key
     */
    public Map<Long, Ship> getShips(Map<Long, ShipRecord> shipRecordMap) {
        Map<Long, Ship> result = Maps.newHashMap();

        for (Map.Entry<Long, ShipRecord> recordEntry : shipRecordMap.entrySet()) {
            ShipRecord value = recordEntry.getValue();

            Class<? extends Ship> shipClass = value.getShipClass();
            Ship ship;
            try {
                Constructor<? extends Ship> constructor = shipClass.getConstructor(GameInstance.class, Vector2.class, Vector2.class, Integer.TYPE);
                ship = constructor.newInstance(gameInstance, value.getPosition(), value.getFacing(), value.getTeamNumber());
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
                log.error("Ship class constructor not found. Creating fantom ship", e);
                ship = new FantomShip(gameInstance, value.getPosition(), value.getFacing(), value.getTeamNumber());
            }

            result.put(recordEntry.getKey(), ship);
        }

        return result;
    }

    @Override
    public GameInstance getGameInstance() {
        return gameInstance;
    }
}
