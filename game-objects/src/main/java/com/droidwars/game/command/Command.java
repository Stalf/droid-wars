package com.droidwars.game.command;

/**
 * Реализует шаблон проектирования Команда
 */
public interface Command<T> {

    /**
     * Исполнение команды
     * @param subject объект, к которому применяется команда
     * @param delta промежуток времени, прошедший с предыдущего такта
     */
    void execute(T subject, float delta);

    /**
     * @return тип команды
     */
    CommandType getType();

}
