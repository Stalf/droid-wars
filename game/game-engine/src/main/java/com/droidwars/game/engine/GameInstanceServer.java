package com.droidwars.game.engine;

import com.droidwars.game.GameInstance;

import java.util.UUID;

/**
 * Server-side game instance interface.
 * Classes implementing this interface are designed to run battles on server-side and record battle data.
 */
public interface GameInstanceServer extends GameInstance, Runnable {

    /**
     * Returns battle UUID
     */
    public UUID getBattleId();
}
