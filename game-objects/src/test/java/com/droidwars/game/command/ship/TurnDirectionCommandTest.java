package com.droidwars.game.command.ship;

import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.TestConstants;
import com.droidwars.game.objects.ships.Ship;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TurnDirectionCommandTest {

    private Ship ship;

    @BeforeMethod
    public void setupTest() {
        ship = new Ship(0L, new Vector2(0,0), new Vector2(1,0));
    }

    @DataProvider
    public Object[][] getData() {
        return new Object[][]{
                {1, new Vector2(0, 1), 9},
                {-1, new Vector2(0, -1), 9},
                {1, new Vector2(-1, 0), 18},
                {-1, new Vector2(-1, 0), 18}
        };
    }

    @Test(dataProvider = "getData")
    public void shipShouldTurnToDesiredDirection(int direction, Vector2 result, int turnTime) {
        float time = 0f;
        float STEP = 0.01f;

        do {
            time += STEP;

            ship.command(new TurnDirectionCommand(direction));

            ship.update(STEP);

        } while (time < turnTime);

        Assert.assertEquals(ship.getFacing().x, result.x, TestConstants.EPSILON);
        Assert.assertEquals(ship.getFacing().y, result.y, TestConstants.EPSILON);
    }

}
