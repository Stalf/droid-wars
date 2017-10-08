package com.droidwars.game.command;

import com.droidwars.game.objects.ships.Ship;

import java.util.Map;

/**
 * Интерфейс объектов, имеющих исполнитель команд
 */
public interface Manageable<T> {

    /**
     * @return исполнителя команд
     */
    CommandExecutor<T> getCommandExecutor();

    /**
     * Добавляет команду
     */
    void command(Command<T> command);

    /**
     * Добавляет список команд в порядке, определенном перечислением {@link CommandType}
     */
    void command(Map<CommandType, Command<Ship>> commands);

}
