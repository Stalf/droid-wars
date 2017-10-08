package com.droidwars.game.engine.factory.game;

import com.droidwars.game.engine.FileStorage;
import com.droidwars.game.engine.GameInstanceServer;
import com.droidwars.game.engine.factory.ship.RandomPositionShipFactory;
import com.droidwars.game.generator.SimpleIdGenerator;
import com.droidwars.game.record.pojo.POJOGameRecordWriterImpl;

import java.nio.file.Paths;

public class SimpleGameInstanceServerFactory extends AbstractGameInstanceFactory {

    public static GameInstanceServer createGameInstance() {

        GameInstanceServerImpl instance = new GameInstanceServerImpl(
                new SimpleIdGenerator(),
                new POJOGameRecordWriterImpl(), new FileStorage(Paths.get("")));

        instance.setShipFactory(new RandomPositionShipFactory(instance));

        return instance;
    }

}
