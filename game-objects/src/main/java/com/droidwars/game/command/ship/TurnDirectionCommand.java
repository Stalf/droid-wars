package com.droidwars.game.command.ship;

import com.droidwars.game.command.Command;
import com.droidwars.game.command.CommandType;
import com.droidwars.game.objects.ships.Ship;

/**
 * Команда на поворот корабля
 */
public class TurnDirectionCommand implements Command<Ship> {

    private int direction;

    /**
     * Поворачивает корабль в указанном направлении на максимально возможный за такт угол.
     * @param direction направление поворота: 1 - против часовой стрелки; -1 - по часовой стрелке
     */
    public TurnDirectionCommand(int direction) {
        this.direction = direction;
    }

    @Override
    public void execute(Ship subject, float delta) {
        subject.getFacing().rotate(direction * subject.getTurnSpeed() * delta).nor();
    }

    @Override
    public CommandType getType() {
        return CommandType.TURN;
    }
}
