package com.droidwars.game.record;

import java.util.List;
import java.util.UUID;

/**
 * Represents a storage instance for game records. It can be file storage, database or REST-based storage connector
 */
public interface GameRecordStorage {

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
    UUID store(BattleRecordMetadata recordMetadata);

}
