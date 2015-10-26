package com.droidwars.game.engine.utils;

import com.badlogic.gdx.math.Vector2;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GameUtilsTest {

    @Test
    public void generateRandomDirectionTest() {
        Vector2 vector2 = GameUtils.generateRandomDirection();

        assertNotNull(vector2);
        Assert.assertEquals(1f, vector2.len(), 0.0001f);

    }

    @Test
    public void generateRandomPositionTest() {
        Vector2 vector2 = GameUtils.generateRandomPosition();

        assertNotNull(vector2);

        assertTrue(vector2.x < Constants.MAP_WIDTH);
        assertTrue(vector2.x >= 0);
        assertTrue(vector2.y < Constants.MAP_WIDTH);
        assertTrue(vector2.y >= 0);

    }

}
