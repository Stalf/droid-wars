package com.droidwars.game.objects.ships;

import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.TestConstants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class ShipTest {

    private Ship ship;

    @Before
    public void setupTest() {
        ship = spy(new Ship(0L, new Vector2(0,0), new Vector2(1,0)));
    }

    @Test
    public void shipShouldDestroyWhenHullPointsLessThenZero() {

        ship.applyDamage(40);
        ship.update(TestConstants.DELTA_STEP);
        ship.applyDamage(50);
        ship.update(TestConstants.DELTA_STEP);
        ship.applyDamage(60);
        ship.update(TestConstants.DELTA_STEP);
        ship.applyDamage(70);
        ship.update(TestConstants.DELTA_STEP);

        verify(ship, atLeast(1)).destroy();

        Assert.assertEquals(ship.getHullPoints(), 0, TestConstants.EPSILON);

    }

}
