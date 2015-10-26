package com.droidwars.game.objects.ships;

import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.TestConstants;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

@Slf4j
public class ShipTest {

    private Ship ship;

    @BeforeMethod
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
