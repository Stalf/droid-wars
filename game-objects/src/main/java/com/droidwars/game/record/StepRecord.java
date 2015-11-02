package com.droidwars.game.record;

import com.droidwars.game.command.Command;
import com.droidwars.game.command.CommandType;
import com.droidwars.game.objects.GameObject;
import com.droidwars.game.objects.ships.Ship;
import com.google.common.collect.Maps;
import lombok.Getter;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.HashMap;

/**
 * Класс для сохранения информации об одном игровом цикле
 */
@Getter
public class StepRecord implements Serializable {

    private HashMap<Long, EnumMap<CommandType, Command<Ship>>> shipCommands = Maps.newHashMap();
    private float delta;

    public StepRecord(float delta) {
        this.delta = delta;
    }

    /**
     * Сохраняет команду кораблю
     *
     * @param subject - объект (корабль)
     * @param command - команда
     */
    public void record(GameObject subject, Command<Ship> command) {
        EnumMap<CommandType, Command<Ship>> objectEnumMap;
        long gameId = subject.getGameId();
        if (!shipCommands.containsKey(gameId)) {
            objectEnumMap = new EnumMap<>(CommandType.class);
            shipCommands.put(gameId, objectEnumMap);
        } else {
            objectEnumMap = shipCommands.get(gameId);
        }

        objectEnumMap.put(command.getType(), command);
    }

}
