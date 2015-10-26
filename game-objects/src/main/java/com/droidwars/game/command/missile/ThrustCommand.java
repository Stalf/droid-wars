package com.droidwars.game.command.missile;

import com.droidwars.game.command.Command;
import com.droidwars.game.command.CommandType;
import com.droidwars.game.objects.projectiles.Missile;

public class ThrustCommand implements Command<Missile> {
    @Override
    public void execute(Missile subject, float delta) {
       //TODO
    }

    @Override
    public CommandType getType() {
        return null;
    }
}
