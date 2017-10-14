package com.droidwars.game.engine.factory.game;

import com.droidwars.game.engine.GameInstanceServer;

/**
 * Abstract factory for creating server-side game instances
 */
public abstract class AbstractGameInstanceFactory {
    /**
     * Creates server-side game instance
     */
    public abstract GameInstanceServer createGameInstance();
}
