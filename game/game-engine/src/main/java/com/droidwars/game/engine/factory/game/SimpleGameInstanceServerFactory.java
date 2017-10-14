package com.droidwars.game.engine.factory.game;

import com.droidwars.game.engine.FileStorage;
import com.droidwars.game.engine.GameInstanceServer;
import com.droidwars.game.engine.factory.ship.RandomPositionShipFactory;
import com.droidwars.game.generator.SimpleIdGenerator;
import com.droidwars.game.record.pojo.POJORecordWriterImpl;

import java.nio.file.Path;

public class SimpleGameInstanceServerFactory extends AbstractGameInstanceFactory {

    private final Path rootPath;

    public SimpleGameInstanceServerFactory(Path rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public GameInstanceServer createGameInstance() {

        GameInstanceServerImpl instance = new GameInstanceServerImpl(
                new SimpleIdGenerator(),
                new POJORecordWriterImpl(), new FileStorage(rootPath));

        instance.setShipFactory(new RandomPositionShipFactory(instance));

        return instance;
    }

}
