package com.droidwars.game.command.ship;

import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.command.Command;
import com.droidwars.game.command.CommandType;
import com.droidwars.game.objects.ships.Ship;
import lombok.NonNull;

/**
 * Command to rotate a ship
 */
public class TurnVectorCommand implements Command<Ship> {

    private Vector2 direction;

    /**
     * Rotates a ship in Vector direction with maximum possible rotation speed per tick.
     * @param direction target direction vector
     */
    public TurnVectorCommand(@NonNull Vector2 direction) {
        this.direction = direction;
    }

    @Override
    public void execute(Ship subject, float delta) {
        float angle = subject.getFacing().angle(direction);

        subject.getFacing().rotate(Math.signum(angle) * Math.min(subject.getTurnSpeed() * delta, Math.abs(angle))).nor();

    }

    @Override
    public CommandType getType() {
        return CommandType.TURN;
    }

}
