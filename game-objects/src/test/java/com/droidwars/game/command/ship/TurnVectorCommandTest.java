package com.droidwars.game.command.ship;

import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.TestConstants;
import com.droidwars.game.objects.ships.Ship;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.mockito.Mockito.spy;

public class TurnVectorCommandTest {

    private Ship ship;

    @BeforeMethod
    public void setupTest() {
        ship = spy(new Ship(0L, new Vector2(0,0), new Vector2(1,0)));
    }

    @DataProvider
    public Object[][] getData() {
        return new Object[][]{{new Vector2(0, 1)}, {new Vector2(0, -1)}};
    }

    @Test(dataProvider = "getData")
    public void shipShouldTurnToDesiredDirection(Vector2 direction) {
        float time = 0f;
        float STEP = 0.1f;

        do {
            time += STEP;

            ship.command(new TurnVectorCommand(direction));

            ship.update(STEP);

            if (ship.getFacing().equals(direction)) {
                Assert.assertEquals(time, 9, TestConstants.EPSILON);
            }
        } while (time < 9);

        Assert.assertEquals(ship.getFacing().x, direction.x, TestConstants.EPSILON);
        Assert.assertEquals(ship.getFacing().y, direction.y, TestConstants.EPSILON);

    }

}
