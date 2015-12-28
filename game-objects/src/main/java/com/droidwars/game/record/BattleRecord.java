package com.droidwars.game.record;

import com.droidwars.game.objects.ships.Ship;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Класс для сохранений всей истории боя
 */
@Getter
public class BattleRecord implements Serializable {

    private Queue<StepRecord> recordList = Lists.newLinkedList();

    private Map<Long, ShipRecord> ships = Maps.newHashMap();

    public BattleRecord(List<Ship> ships) {

        for (Ship ship : ships) {
            this.ships.put(ship.getGameId(), new ShipRecord(ship.getClass(),  ship.getTeamNumber(), ship.getPosition().cpy(), ship.getFacing().cpy()));
        }

    }

    public void add(StepRecord stepRecord) {
        recordList.add(stepRecord);
    }
}
