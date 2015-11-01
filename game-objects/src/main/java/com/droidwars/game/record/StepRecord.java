package com.droidwars.game.record;

import com.droidwars.game.command.Command;
import com.droidwars.game.command.CommandType;
import com.droidwars.game.objects.GameObject;
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

    private HashMap<Long, EnumMap<CommandType, Command>> commands = Maps.newHashMap();
    private float delta;

    public StepRecord(float delta) {
        this.delta = delta;
    }

    /**
     * Сохраняет команду игровому объекту
     *
     * @param subject - объект (корабль, ракета, и т.д.)
     * @param command - команда
     */
    public void record(GameObject subject, Command command) {
        EnumMap<CommandType, Command> objectEnumMap;
        long gameId = subject.getGameId();
        if (!commands.containsKey(gameId)) {
            objectEnumMap = new EnumMap<>(CommandType.class);
            commands.put(gameId, objectEnumMap);
        } else {
            objectEnumMap = commands.get(gameId);
        }

        objectEnumMap.put(command.getType(), command);
    }

}
