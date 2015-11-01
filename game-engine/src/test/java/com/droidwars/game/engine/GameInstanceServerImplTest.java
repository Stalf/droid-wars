package com.droidwars.game.engine;

import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

@Slf4j
public class GameInstanceServerImplTest {


    @Test
    public void gameTest1() {
        GameInstanceServerImpl gameInstance = new GameInstanceServerImpl();
        Thread thread = new Thread(gameInstance);

        thread.start();

        try {
            thread.join();

            Assert.assertEquals(gameInstance.getTime(), 10, 0.01f);
            Assert.assertEquals(gameInstance.getShipList().size(), 2);
        } catch (InterruptedException e) {
            log.error("test error", e);
        }

    }

}
