package com.droidwars.game.engine;

import com.droidwars.game.exceptions.GameException;
import com.droidwars.game.record.BattleRecordMetadata;
import com.droidwars.game.record.RecordStorage;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.RandomBasedGenerator;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.UUID;

@Slf4j
public class FileStorage implements RecordStorage {

    private static final RandomBasedGenerator uuidGenerator =
        Generators.randomBasedGenerator();

    private Path rootPath;

    public FileStorage(Path rootPath) {
        this.rootPath = rootPath;
        if (!rootPath.toFile().exists()) {
            try {
                Files.createDirectory(rootPath.toAbsolutePath());
            } catch (IOException e) {
                throw new RuntimeException("Error creating directory for filestorage", e);
            }
        }
        log.trace("FileStorage initialized with rootPath = {}", rootPath.toAbsolutePath());
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
    public UUID store(BattleRecordMetadata recordMetadata) throws GameException {
        try {
            recordMetadata.save();
        } catch (IOException e) {
            throw new GameException("Error flushing fileOutputStream to disk", e);
        }

        return recordMetadata.getBattleId();
    }

    @Override
    public BattleRecordMetadata createMetadata() throws GameException {
        UUID uuid = uuidGenerator.generate();
        try {
            Path newFilePath = Paths.get(rootPath.toAbsolutePath().toString(), String.format("%s.dat", uuid.toString()));
            log.trace("Battle record filepath = {}", newFilePath.toString());
            return BattleRecordMetadata.newInstance(uuid,
                Files.newOutputStream(newFilePath, StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE));
        } catch (IOException e) {
            throw new GameException("Error creating BattleRecordMetadata", e);
        }

    }
}
