package com.droidwars.game.command;

import java.util.EnumMap;

/**
 * Интерфейс объектов, способных выполнять команды
 */
public interface CommandExecutor<T> {

    /**
     * Добавить команду
     */
    void add(Command<T> command);

    /**
     * @return все отправленные команды
     */
    EnumMap<CommandType, Command<T>> getAll();

    /**
     * Очистить список команд
     */
    void clear();

    /**
     * Удалить команду
     * @param commandType тип команды
     * @return удаленная команда
     */
    Command remove(CommandType commandType);

    /**
     * Исполняет все команды, после чего очищает список
     */
    void execute(float delta);
}
