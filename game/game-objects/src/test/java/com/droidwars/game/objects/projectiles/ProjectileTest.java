package com.droidwars.game.objects.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.AbstractGameInstanceTest;
import com.droidwars.game.TestConstants;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.droidwars.game.TestConstants.DELTA_STEP;
import static org.mockito.Mockito.spy;

public class ProjectileTest extends AbstractGameInstanceTest {

    @Test
    public void shouldNotMoveWithZeroStartingSpeed() {
        Projectile projectile = spy(new TestProjectile(gameInstanceMock, new Vector2(0, 0), new Vector2(1, 0)));

        float time = 0;
        do {
            time += DELTA_STEP;
            projectile.update(DELTA_STEP);
        } while (time < 5);

        Assert.assertEquals(projectile.getTravelDistance(), 0, 1);
        Assert.assertEquals(projectile.getPosition().x, 0, 1);
        Assert.assertEquals(projectile.getPosition().y, 0, TestConstants.EPSILON);
    }

    @Test
    public void shouldMoveWithNonZeroStartingSpeed() {
        Projectile projectile = spy(new TestProjectile(gameInstanceMock, new Vector2(0, 0), new Vector2(1, 0), new Vector2(10, 0)));

        float time = 0;
        do {
            time += DELTA_STEP;
            projectile.update(DELTA_STEP);
        } while (time < 5);

        Assert.assertEquals(projectile.getTravelDistance(), 50, 10);
        Assert.assertEquals(projectile.getPosition().x, 50, 1);
        Assert.assertEquals(projectile.getPosition().y, 0, 1);
    }

    @Test
    public void shouldMoveForwardWithNonZeroBackwardStartingSpeedAndAcceleration() {
        Projectile projectile = spy(new TestProjectile(gameInstanceMock, new Vector2(0, 0), new Vector2(1, 0),
            new Vector2(-50, 0), 10f));

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
    public void shouldGetZeroSpeedFromNonZeroBackwardStartingSpeedInRightTime() {
        Projectile projectile = spy(new TestProjectile(gameInstanceMock, new Vector2(0, 0), new Vector2(1, 0),
            new Vector2(-50, 0), 10f));

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
