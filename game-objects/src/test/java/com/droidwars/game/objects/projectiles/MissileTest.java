package com.droidwars.game.objects.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.TestConstants;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.droidwars.game.TestConstants.DELTA_STEP;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@Slf4j
public class MissileTest {

    public static final int MISSILE_MAX_DISTANCE = 700;
    public static final Vector2 MISSILE_STARTING_VELOCITY = new Vector2(150, 0);
    public static final int MISSILE_DAMAGE = 100;

    @Test
    public void missileShouldDestroyAfterMaxDistanceTravelled() {

        Missile missile = spy(new Missile(0L, new Vector2(0, 0), new Vector2(1, 0), MISSILE_STARTING_VELOCITY, MISSILE_DAMAGE, MISSILE_MAX_DISTANCE, 0));

        float time = 0;
        do {

            time += DELTA_STEP;

            missile.update(DELTA_STEP);

        } while (time < 5);

        verify(missile).destroy();
        Assert.assertEquals(missile.getTravelDistance(), MISSILE_MAX_DISTANCE, 1);
        Assert.assertEquals(missile.getPosition().x, 700, 1);
        Assert.assertEquals(missile.getPosition().y, 0, TestConstants.EPSILON);

    }

    @Test
    public void missileShouldDestroyAfterMaxDistanceTravelledAccelerated() {

        Projectile missile = spy(new Missile(1L, new Vector2(0, 0), new Vector2(1, 0), MISSILE_STARTING_VELOCITY, MISSILE_DAMAGE, MISSILE_MAX_DISTANCE, 50));

        float time = 0;
        do {

            time += DELTA_STEP;

            missile.update(DELTA_STEP);


        } while (time < 4 /*C ускорением должен пролететь быстрее*/);

        verify(missile).destroy();
        Assert.assertEquals(missile.getTravelDistance(), MISSILE_MAX_DISTANCE, 10);
        Assert.assertEquals(missile.getPosition().x, 700, 10);
        Assert.assertEquals(missile.getPosition().y, 0, TestConstants.EPSILON);

    }

}
