package com.droidwars.game.objects.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.TestConstants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class MissileTest {

    public static final int MISSILE_MAX_DISTANCE = 700;
    public static final Vector2 MISSILE_STARTING_VELOCITY = new Vector2(150, 0);
    private Projectile missile;
    private float time;

    @Before
    public void setupTest(){
        missile = spy(new Missile(0L, new Vector2(0,0), new Vector2(1,0), MISSILE_STARTING_VELOCITY, 100, MISSILE_MAX_DISTANCE));
    }

    @Test
    public void missileShouldDestroyAfterMaxDistanceTravelled() {

        do {

            time += TestConstants.DELTA_STEP;

            missile.update(TestConstants.DELTA_STEP);


        } while (time < 10);

        verify(missile).destroy();
        Assert.assertEquals(missile.getTravelDistance(), MISSILE_MAX_DISTANCE, 1);
        Assert.assertEquals(missile.getPosition().x, 700, 1);
        Assert.assertEquals(missile.getPosition().y, 0, TestConstants.EPSILON);

    }

}
