package com.droidwars.game.factory;

import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.GameInstance;
import com.droidwars.game.objects.ships.Ship;
import com.droidwars.game.record.BattleRecord;
import com.droidwars.game.record.ShipRecord;
import com.google.common.collect.Maps;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Фабрика создает корабли в том состоянии, в котором они были сохранены в записи о бое
 */
@Slf4j
@RequiredArgsConstructor
public class BattleRecordShipFactory implements GameObjectFactory {

    @NonNull
    private GameInstance gameInstance;

    /**
     * @param battleRecord запись о бое с исходными данными кораблей
     * @return Map c новыми кораблями. Ключем является gameId кораблей
     */
    public Map<Long, Ship> getShips(BattleRecord battleRecord) {
        Map<Long, Ship> result = Maps.newHashMap();
        Map<Long, ShipRecord> battleRecordShips = battleRecord.getShips();

        for (Map.Entry<Long, ShipRecord> recordEntry : battleRecordShips.entrySet()) {
            ShipRecord value = recordEntry.getValue();

            Class<? extends Ship> shipClass = value.getShipClass();
            Ship ship;
            try {
                Constructor<? extends Ship> constructor = shipClass.getConstructor(GameInstance.class, Vector2.class, Vector2.class, Integer.TYPE);
                ship = constructor.newInstance(gameInstance, value.getPosition(), value.getFacing(), value.getTeamNumber());
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
                log.error("Конструктор класса корабля не найден. Создаем абстрактный корбаль", e);
                ship = new Ship(gameInstance, value.getPosition(), value.getFacing(), value.getTeamNumber());
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
