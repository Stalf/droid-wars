package com.droidwars.game.command;

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

}
