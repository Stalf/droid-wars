package com.droidwars.game.engine;

import com.droidwars.game.record.BattleRecordMetadata;
import com.droidwars.game.record.GameRecordStorage;

import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

public class FileStorage implements GameRecordStorage {

    private Path rootPath;

    public FileStorage(Path rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public List<BattleRecordMetadata> getBattlesList() {
        return null;
    }

    @Override
    public BattleRecordMetadata findBattleById(UUID uuid) {
        return null;
    }
            
    @Override
    public UUID store(BattleRecordMetadata recordMetadata) {
        return null;
    }
}
