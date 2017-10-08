package com.droidwars.player.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.droidwars.player.DroidWarsGame;
import com.droidwars.player.utils.Constants;

public class DesktopLauncher {

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = Constants.APP_WIDTH;
        config.height = Constants.APP_HEIGHT;
        config.title = Constants.GAME_NAME;

//        config.resizable = false;

        new LwjglApplication(new DroidWarsGame(), config);
    }

}
