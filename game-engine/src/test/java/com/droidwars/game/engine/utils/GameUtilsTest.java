package com.droidwars.game.engine.utils;

import com.badlogic.gdx.math.Vector2;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GameUtilsTest {

    @Test
    public void generateRandomDirectionTest() {
        Vector2 vector2 = GameUtils.generateRandomDirection();

        Assert.assertNotNull(vector2);
        Assert.assertEquals(vector2.len(), 1f, 0.001f);

    }

    @Test
    public void generateRandomPositionTest() {
        Vector2 vector2 = GameUtils.generateRandomPosition();

        Assert.assertNotNull(vector2);
        Assert.assertTrue(vector2.x <= Constants.MAP_WIDTH);
        Assert.assertTrue(vector2.x >= 0);
        Assert.assertTrue(vector2.y <= Constants.MAP_WIDTH);
        Assert.assertTrue(vector2.y >= 0);

    }

}
