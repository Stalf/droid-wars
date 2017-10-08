package com.droidwars.player.background;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.droidwars.player.Resources;

public class BackgroundFXRenderer {

    SpriteBatch backgroundBatch;
    Sprite background;

    public BackgroundFXRenderer() {

        background = Resources.getInstance().background;
        backgroundBatch = new SpriteBatch();
        backgroundBatch.getProjectionMatrix().setToOrtho2D(0, 0, 1500, 1500);

    }

    public void render() {
        backgroundBatch.begin();
        background.draw(backgroundBatch);
        backgroundBatch.end();
    }

}
