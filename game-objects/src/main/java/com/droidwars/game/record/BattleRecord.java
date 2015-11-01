package com.droidwars.game.record;

import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.objects.ships.Ship;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Класс для сохранений всей истории боя
 */
@Getter
public class BattleRecord implements Serializable {

    private List<StepRecord> recordList = Lists.newLinkedList();

    private Map<Long, Class> ships = Maps.newHashMap();
    private Map<Long, Vector2> positions = Maps.newHashMap();
    private Map<Long, Vector2> facings = Maps.newHashMap();

    public BattleRecord(List<Ship> ships) {

        for (Ship ship : ships) {
            this.ships.put(ship.getGameId(), ship.getClass());
            positions.put(ship.getGameId(), ship.getPosition().cpy());
            facings.put(ship.getGameId(), ship.getFacing().cpy());
        }

    }

    public void add(StepRecord stepRecord) {
        recordList.add(stepRecord);
    }
}
