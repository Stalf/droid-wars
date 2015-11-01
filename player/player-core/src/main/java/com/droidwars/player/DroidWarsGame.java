package com.droidwars.player;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.droidwars.player.screens.GameScreen;

public class DroidWarsGame extends Game {

    @Override
    public void create() {

        Gdx.app.setLogLevel(Application.LOG_INFO);

        setScreen(new GameScreen(this));
    }
}
