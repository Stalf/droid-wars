package com.droidwars.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

public class Resources {

    public Sprite background = new Sprite(new Texture(Gdx.files.internal("sky.jpg")));
    public Sprite explosion = new Sprite(new Texture(Gdx.files.internal("explosion.png")));
    public Sprite spark = new Sprite(new Texture(Gdx.files.internal("spark.png")));
    public Sprite bubble = new Sprite(new Texture(Gdx.files.internal("bubble.png")));
    public Sprite bigbubble = new Sprite(new Texture(Gdx.files.internal("bigbubble.png")));


    public Array<Sprite> frigates = new Array<Sprite>();
    public Array<Sprite> missiles = new Array<Sprite>();

    private static Resources instance;

    public Resources() {

        frigates.add(new Sprite(new Texture(Gdx.files.internal("frigatep1.png"))));
        frigates.add(new Sprite(new Texture(Gdx.files.internal("frigatep2.png"))));
        frigates.add(new Sprite(new Texture(Gdx.files.internal("frigatep3.png"))));
        frigates.add(new Sprite(new Texture(Gdx.files.internal("frigatep4.png"))));

        missiles.add(new Sprite(new Texture(Gdx.files.internal("bomberp1.png"))));
        missiles.add(new Sprite(new Texture(Gdx.files.internal("bomberp2.png"))));
        missiles.add(new Sprite(new Texture(Gdx.files.internal("bomberp3.png"))));
        missiles.add(new Sprite(new Texture(Gdx.files.internal("bomberp4.png"))));

    }

    public static Resources getInstance() {
        if (instance == null) {
            instance = new Resources();
        }
        return instance;
    }

}
