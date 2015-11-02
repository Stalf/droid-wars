package com.droidwars.game.objects.ships;

import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.AbstractGameInstanceTest;
import com.droidwars.game.TestConstants;
import com.droidwars.game.command.CommandExecutorImpl;
import com.droidwars.game.command.ship.TurnDirectionCommand;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Field;

import static com.droidwars.game.TestConstants.DELTA_STEP;
import static org.mockito.Mockito.*;

@Slf4j
public class ShipTest extends AbstractGameInstanceTest {

    private Ship ship;

    @BeforeMethod
    public void setupTest() {
        ship = spy(new Ship(gameInstance, new Vector2(0,0), new Vector2(1,0), 1));
    }

    @Test
    public void shipShouldDestroyWhenHullPointsLessThenZero() {

        ship.applyDamage(40);
        ship.update(DELTA_STEP);
        ship.applyDamage(50);
        ship.update(DELTA_STEP);
        ship.applyDamage(60);
        ship.update(DELTA_STEP);
        ship.applyDamage(70);
        ship.update(DELTA_STEP);

        verify(ship, atLeast(1)).destroy();

        Assert.assertEquals(ship.getHullPoints(), 0, TestConstants.EPSILON);

    }

    @Test
    public void shipShouldNotExecuteCommandsAfterDestroy() throws NoSuchFieldException, IllegalAccessException {

        CommandExecutorImpl<Ship> commandExecutor = new CommandExecutorImpl<>(ship);

        Field privateField = Ship.class.getDeclaredField("commandExecutor");
        privateField.setAccessible(true);
        privateField.set(ship, commandExecutor);

        ship.applyDamage(10);
        ship.update(DELTA_STEP);

        TurnDirectionCommand command1 = spy(new TurnDirectionCommand(1));
        ship.command(command1);

        ship.applyDamage(50);
        ship.update(DELTA_STEP);

        ship.applyDamage(50);
        ship.update(DELTA_STEP);
        verify(ship, atLeast(1)).destroy();
        Assert.assertEquals(ship.getHullPoints(), 0, TestConstants.EPSILON);

        TurnDirectionCommand command2 = spy(new TurnDirectionCommand(1));
        ship.command(command2);
        ship.applyDamage(50);
        ship.update(DELTA_STEP);

        verify(command1, times(1)).execute(any(Ship.class), anyFloat());
        verify(command2, never()).execute(any(Ship.class), anyFloat());


    }

}
