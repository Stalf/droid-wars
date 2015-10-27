package com.droidwars.game.objects.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.TestConstants;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.droidwars.game.TestConstants.DELTA_STEP;
import static org.mockito.Mockito.spy;

public class ProjectileTest {

    private Projectile projectile;

    @BeforeMethod
    public void setupTest() {
        projectile = spy(new Projectile(0L, new Vector2(0, 0), new Vector2(1, 0)));
    }

    @Test
    public void zeroStartingSpeed() {

        float time = 0;
        do {

            time += DELTA_STEP;

            projectile.update(DELTA_STEP);

        } while (time < 5);

        Assert.assertEquals(projectile.getTravelDistance(), 125, 1);
        Assert.assertEquals(projectile.getPosition().x, 125, 1);
        Assert.assertEquals(projectile.getPosition().y, 0, TestConstants.EPSILON);

    }

    @Test
    public void nonZeroStartingSpeed() {

        projectile.getVelocity().set(0, 50);

        float time = 0;
        do {

            time += DELTA_STEP;

            projectile.update(DELTA_STEP);

        } while (time < 5);

        Assert.assertEquals(projectile.getTravelDistance(), 280, 10);
        Assert.assertEquals(projectile.getPosition().x, 125, 1);
        Assert.assertEquals(projectile.getPosition().y, 250, 1);

    }

    @Test
    public void nonZeroBackwardStartingSpeed() {

        projectile.getVelocity().set(-50, 0);

        float time = 0;
        do {

            time += DELTA_STEP;

            projectile.update(DELTA_STEP);

        } while (time < 10);

        Assert.assertEquals(projectile.getTravelDistance(), 250, 1);
        Assert.assertEquals(projectile.getPosition().x, 0, 1);
        Assert.assertEquals(projectile.getPosition().y, 0, 1);

    }

    @Test
    public void nonZeroBackwardStartingSpeedToZeroSpeed() {

        projectile.getVelocity().set(-50, 0);

        float time = 0;
        do {

            time += DELTA_STEP;

            projectile.update(DELTA_STEP);

        } while (time < 5);

        Assert.assertEquals(projectile.getTravelDistance(), 125, 1);
        Assert.assertEquals(projectile.getVelocity().x, 0, 1);
        Assert.assertEquals(projectile.getVelocity().y, 0, 1);

    }

}
