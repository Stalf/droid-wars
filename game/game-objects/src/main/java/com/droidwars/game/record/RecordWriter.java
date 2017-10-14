package com.droidwars.game.record;

import com.droidwars.game.command.Command;
import com.droidwars.game.exceptions.GameException;
import com.droidwars.game.objects.GameObject;
import com.droidwars.game.objects.ships.Ship;

import java.util.List;

/**
 * Battle course writer
 */
public interface RecordWriter {

    /**
     * Starts recording the battle. Saves ship positions, map configuration and so on
     * @param recordMetadata record metadata representing main battle parameters
     * @param ships list of Ship objects
     */
    void startRecord(BattleRecordMetadata recordMetadata, List<Ship> ships);

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
     * Stops battle recording. Flushes data to metadatas` inner outputstream
     * @return battle metadata ready for storing in {@code GameRecordStorage}
     */
    BattleRecordMetadata stopRecord() throws GameException;

}
