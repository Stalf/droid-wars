package com.droidwars.game.engine;

import com.droidwars.game.engine.factory.game.SimpleGameInstanceServerFactory;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

@Slf4j
public class GameInstanceServerImplTest {


    @Test
    public void gameTest1() {

        GameInstanceServer gameInstance = SimpleGameInstanceServerFactory.createGameInstance();
        gameInstance.run();

        Assert.assertEquals(gameInstance.getTime(), 10, 0.01f);

    }

}
