package com.droidwars.game.command;

import java.io.Serializable;

/**
 * Реализует шаблон проектирования Команда
 */
public interface Command<T> extends Serializable {

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
