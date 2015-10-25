package com.droidwars.game.engine;

import com.droidwars.game.engine.factory.AbstractShipFactory;
import com.droidwars.game.engine.factory.RandomPositionShipFactory;
import com.droidwars.game.engine.utils.Constants;
import com.droidwars.game.generator.SimpleIdGenerator;
import com.droidwars.game.objects.ships.Ship;
import com.droidwars.game.objects.ships.ShipType;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

/**
 * Основной класс, содержащий информацию о боевой части игры.
 * Для каждого боя формируется один экземпляр этого класса.
 * Реализует основной жизненный цикл игры
 */
@Slf4j
public class GameInstance implements Runnable {

    private final SimpleIdGenerator idGenerator = new SimpleIdGenerator();
    private List<Ship> shipList = new LinkedList<>();
    private AbstractShipFactory shipFactory;
    private float time = 0f;

    public GameInstance() {
        shipFactory = new RandomPositionShipFactory(idGenerator);
    }

    /**
     * Инициализирует бой. Расставляет объекты
     */
    public void startBattle() {

        log.debug("Battle start");

        time = 0f;

        shipList.add(shipFactory.getShip(ShipType.FRIGATE));

    }

    public void update(float delta) {
        time += delta;

        if (log.isDebugEnabled()) {
            log.debug("Game step, time: {0}", time);
        }

        for (Ship next: shipList) {
            next.update(delta);
        }

    }

    @Override
    public void run() {

        log.debug("Game instance thread start");

        startBattle();

        do {

            if (!Thread.interrupted()) {
                update(Constants.DELTA_STEP);
            } else {
                log.debug("Game thread interrupted");
                return;
            }

        } while (time < 10);
    }
}
