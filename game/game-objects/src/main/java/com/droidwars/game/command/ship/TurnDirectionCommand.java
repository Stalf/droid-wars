package com.droidwars.game.command.ship;

import com.droidwars.game.command.Command;
import com.droidwars.game.command.CommandType;
import com.droidwars.game.objects.ships.Ship;

/**
 * Command to rotate a ship
 */
public class TurnDirectionCommand implements Command<Ship> {

    private int direction;

    /**
     * Rotates a ship in specified direction on a maximum possible per tick angle.
     *
     * @param direction of rotation:
     *                  <ul>
     *                  <li>-1 - counter clockwise</li>
     *                  <li>0 - do not rotate</li>
     *                  <li>1 - clockwise</li>
     *                  </ul>
     */
    public TurnDirectionCommand(int direction) {
        if (Math.abs(direction) > 1) {
            throw new IllegalArgumentException("Only values [-1, 0, 1] are acceptable as a rotation direction");
        }
        this.direction = direction;
    }

    @Override
    public void execute(Ship subject, float delta) {
        subject.getFacing().rotate(-direction * subject.getTurnSpeed() * delta).nor();
    }

    @Override
    public CommandType getType() {
        return CommandType.TURN;
    }
}
