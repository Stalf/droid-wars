package com.droidwars.game.command.ship;

import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.AbstractGameInstanceTest;
import com.droidwars.game.TestConstants;
import com.droidwars.game.objects.ships.Ship;
import com.droidwars.game.objects.ships.TestShip;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TurnVectorCommandTest extends AbstractGameInstanceTest {

    private Ship ship;

    @BeforeMethod
    public void setupTest() {
        ship = new TestShip(gameInstanceMock, new Vector2(0, 0), new Vector2(1, 0), 1);
    }

    @DataProvider
    public Object[][] getData() {
        return new Object[][]{
                {new Vector2(0, 1), 9},
                {new Vector2(0, -1), 9},
            {new Vector2(-1, 0), 18},
            {new Vector2(-1, 0), 30}
        };
    }

    @Test(dataProvider = "getData")
    public void shipShouldTurnToDesiredDirection(Vector2 direction, int turnTime) {
        float time = 0f;
        float STEP = 0.1f;

        do {
            time += STEP;

            ship.command(new TurnVectorCommand(direction));

            ship.update(STEP);

        } while (time < turnTime);

        Assert.assertEquals(ship.getFacing().x, direction.x, TestConstants.EPSILON);
        Assert.assertEquals(ship.getFacing().y, direction.y, TestConstants.EPSILON);

    }

}
