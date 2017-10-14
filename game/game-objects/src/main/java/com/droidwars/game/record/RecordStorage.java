package com.droidwars.game.record;

import com.droidwars.game.exceptions.GameException;

import java.util.List;
import java.util.UUID;

/**
 * Represents a storage instance for game records. It can be file storage, database or REST-based storage connector
 */
public interface RecordStorage {

    /**
     * @return retrieve complete list of battles in this store
     */
    List<BattleRecordMetadata> getBattlesList();

    /**
     * Searches for a battle in this store
     * @param uuid battle identifier
     * @return BattleRecordMetadata object or {@code null} if nothing was found
     */
    BattleRecordMetadata findBattleById(UUID uuid);

    /**
     * Stores a battle into this storage
     * @param recordMetadata complete stack of data for storing
     * @return UUID storage identifier if stored successfully
     */
    UUID store(BattleRecordMetadata recordMetadata) throws GameException;

    /**
     * Creates a new record metadata, connected to this storage
     * @return metadata object for writer operations
     * @throws GameException if storage was unable to prepare record
     */
    BattleRecordMetadata createMetadata() throws GameException;
}
