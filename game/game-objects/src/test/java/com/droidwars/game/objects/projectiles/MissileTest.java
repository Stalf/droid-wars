package com.droidwars.game.objects.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.AbstractGameInstanceTest;
import com.droidwars.game.TestConstants;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static com.droidwars.game.TestConstants.DELTA_STEP;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Slf4j
public class MissileTest extends AbstractGameInstanceTest {

    private static final Float MISSILE_MAX_DISTANCE = 700f;
    private static final Vector2 MISSILE_STARTING_VELOCITY = new Vector2(150, 0);
    private static final int MISSILE_DAMAGE = 100;

    @Test
    public void missileShouldBeAliveIfNotTravelledMaxDistance() {

        Projectile missile = spy(new Missile(gameInstanceMock,
            new Vector2(0, 0), new Vector2(1, 0),
            MISSILE_STARTING_VELOCITY, MISSILE_DAMAGE, MISSILE_MAX_DISTANCE, 0));
        assertTrue(missile.isAlive());

        float time = 0;
        do {
            time += DELTA_STEP;
            missile.update(DELTA_STEP);

        } while (time < 2);

        verify(missile, never()).destroy();
        assertTrue(missile.isAlive());
        assertThat(missile.getTravelDistance(), Matchers.lessThan(MISSILE_MAX_DISTANCE));
        assertEquals(missile.getPosition().y, 0, TestConstants.EPSILON);

    }

    @Test
    public void missileShouldBeSelfDestroyedAfterMaxDistanceTravelled() {

        Missile missile = spy(new Missile(gameInstanceMock,
            new Vector2(0, 0), new Vector2(1, 0),
            MISSILE_STARTING_VELOCITY, MISSILE_DAMAGE, MISSILE_MAX_DISTANCE, 0));
        assertTrue(missile.isAlive());

        float time = 0;
        do {
            time += DELTA_STEP;
            missile.update(DELTA_STEP);
        } while (time < 5);

        verify(missile).destroy();
        assertFalse(missile.isAlive());
        assertEquals(missile.getTravelDistance(), MISSILE_MAX_DISTANCE, 1);
        assertEquals(missile.getPosition().x, 700, 1);
        assertEquals(missile.getPosition().y, 0, TestConstants.EPSILON);

    }

    @Test
    public void missileShouldBeSelfDestroyedAfterMaxDistanceTravelledAccelerated() {

        Projectile missile = spy(new Missile(gameInstanceMock,
            new Vector2(0, 0), new Vector2(1, 0),
            MISSILE_STARTING_VELOCITY, MISSILE_DAMAGE, MISSILE_MAX_DISTANCE, 50));
        assertTrue(missile.isAlive());

        float time = 0;
        do {
            time += DELTA_STEP;
            missile.update(DELTA_STEP);

        } while (time < 4 /*With acceleration should reach maxDistance faster*/);

        verify(missile).destroy();
        assertFalse(missile.isAlive());
        assertEquals(missile.getTravelDistance(), MISSILE_MAX_DISTANCE, 10);
        assertEquals(missile.getPosition().x, 700, 10);
        assertEquals(missile.getPosition().y, 0, TestConstants.EPSILON);
    }

}
