package com.droidwars.player.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.droidwars.player.GameInstancePlayerImpl;
import com.droidwars.player.objects.ships.ShipSprite;

public class HUD {

    private BitmapFont font;
    private SpriteBatch HUDBatch;

    private int width;
    private int height;

    public HUD() {

        HUDBatch = new SpriteBatch();
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

    }

    public void resize(int width, int height) {
        this.width = width;
        this.height = height;

        HUDBatch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
    }

    public void render() {

        HUDBatch.begin();
        font.setColor(Color.WHITE);
        font.draw(HUDBatch, "fps: " + Gdx.graphics.getFramesPerSecond(), 10, 20);
        font.draw(HUDBatch, String.format("gameTime: %.2f", GameInstancePlayerImpl.getInstance().getGameTime()), 10, 35);
        font.draw(HUDBatch, String.format("playerTime: %.2f", GameInstancePlayerImpl.getInstance().getPlayerTime()), 10, 50);

        font.setColor(Color.SKY);
        ShipSprite ship1 = GameInstancePlayerImpl.getInstance().getShipSpriteList().get(0);
//        font.draw(HUDBatch, ship1.ai.getName(), 10, height - 10);
        font.draw(HUDBatch, String.format("speed: %.2f", ship1.getSubject().getVelocity().len()), 10, height - 25);
        font.draw(HUDBatch, ship1.getHealth(), 10, height - 40);

        font.setColor(Color.PINK);
        ShipSprite ship2 = GameInstancePlayerImpl.getInstance().getShipSpriteList().get(1);
//        font.draw(HUDBatch, ship2.ai.getName(), width - 200, height - 10);
        font.draw(HUDBatch, String.format("speed: %.2f", ship2.getSubject().getVelocity().len()), width - 200, height - 25);
        font.draw(HUDBatch, ship2.getHealth(), width - 200, height - 40);
        HUDBatch.end();

    }

    /*public void renderGameOver() {
        HUDBatch.begin();
        font.setColor(Color.GOLD);
        font.draw(HUDBatch, "GAME OVER", 350, 350);
        if (GameInstancePlayerImpl.getInstance().ships.get(0).alive) {
            font.setColor(Color.SKY);
            font.draw(HUDBatch, "Blue player wins", 340, 300);
        } else {
            font.setColor(Color.PINK);
            font.draw(HUDBatch, "Red player wins", 340, 300);
        }
        HUDBatch.end();
    }*/

}
