package com.droidwars.game.record.pojo;

import com.droidwars.game.objects.ships.Ship;
import com.droidwars.game.record.ShipRecord;
import com.droidwars.game.record.StepRecord;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Battle history data storage class
 */
@Getter
class BattleRecord implements Serializable {

    private Queue<StepRecord> recordList = Lists.newLinkedList();

    private Map<Long, ShipRecord> ships = Maps.newHashMap();

    BattleRecord(List<Ship> ships) {

        for (Ship ship : ships) {
            this.ships.put(ship.getGameId(), new ShipRecord(ship.getClass(),  ship.getTeamNumber(), ship.getPosition().cpy(), ship.getFacing().cpy()));
        }

    }

    void add(StepRecord stepRecord) {
        recordList.add(stepRecord);
    }
}
