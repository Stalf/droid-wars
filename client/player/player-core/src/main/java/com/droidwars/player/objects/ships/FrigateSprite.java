package com.droidwars.player.objects.ships;

import com.droidwars.game.objects.ships.Frigate;
import com.droidwars.player.Resources;

/**
 * Графическое представление фрегата
 */
public class FrigateSprite extends ShipSprite {

    public FrigateSprite(Frigate subject) {
        super(subject);

        this.setSprite(Resources.getInstance().frigates.get(subject.getTeamNumber()));
    }
}
