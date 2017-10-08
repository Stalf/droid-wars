package com.droidwars.game.record;

import com.droidwars.game.command.Command;
import com.droidwars.game.objects.GameObject;
import com.droidwars.game.objects.ships.Ship;

import java.util.List;

/**
 * Battle course writer
 */
public interface GameRecordWriter {

    /**
     * Starts recording the battle. Saves ship positions, map configuration and so on
     * @param ships list of Ship objects
     */
    void startRecord(List<Ship> ships);

    /**
     * Saves a command issued to a game object
     * @param subject commanded {@code GameObject}
     * @param command {@code Command} object
     */
    void write(GameObject subject, Command<Ship> command);

    /**
     * Starts recording of a new game step
     * @param delta step length
     */
    void stepBegin(float delta);

    /**
     * Finishes game step
     */
    void stepEnd();

    /**
     * Stops battle recording
     * @return battle metadata ready for storing in {@code GameRecordStorage}
     */
    BattleRecordMetadata stopRecord();

}
