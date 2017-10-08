package com.droidwars.game.engine.factory.game;

import com.droidwars.game.command.ship.TurnDirectionCommand;
import com.droidwars.game.engine.GameInstanceServer;
import com.droidwars.game.engine.factory.ship.AbstractShipFactory;
import com.droidwars.game.engine.utils.Constants;
import com.droidwars.game.generator.IdGenerator;
import com.droidwars.game.objects.ships.Ship;
import com.droidwars.game.objects.ships.ShipType;
import com.droidwars.game.record.GameRecordStorage;
import com.droidwars.game.record.GameRecordWriter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

/**
 * Для каждого боя формируется один экземпляр этого класса.
 * Реализует основной жизненный цикл игры.
 */
@Slf4j
public class GameInstanceServerImpl implements GameInstanceServer {

    @Getter
    private final IdGenerator idGenerator;
    @Getter
    private final GameRecordWriter gameRecordWriter;
    @Getter
    private float time = 0f;

    private final GameRecordStorage recordStorage;

    @Setter(AccessLevel.PACKAGE)
    private AbstractShipFactory shipFactory;
    private List<Ship> shipList = new LinkedList<>();

    GameInstanceServerImpl(IdGenerator idGenerator, GameRecordWriter gameRecordWriter, GameRecordStorage recordStorage) {
        this.idGenerator = idGenerator;
        this.gameRecordWriter = gameRecordWriter;
        this.recordStorage = recordStorage;
    }

    /**
     * Инициализирует бой. Расставляет объекты
     */
    private void startBattle() {

        log.debug("Battle start");

        time = 0f;

        shipList.add(shipFactory.getShip(ShipType.FRIGATE, 1));
        shipList.add(shipFactory.getShip(ShipType.FRIGATE, 2));

        gameRecordWriter.startRecord(shipList);
    }

    private void stopBattle() {
        recordStorage.store(gameRecordWriter.stopRecord());
    }

    private void update(float delta) {
        time += delta;

        if (log.isTraceEnabled()) {
            log.trace("Game step, time: {}; delta: {}", time, delta);
        }

        gameRecordWriter.stepBegin(delta);

        for (Ship ship: shipList) {
            ship.command(new TurnDirectionCommand(1));
            ship.update(delta);
        }

        gameRecordWriter.stepEnd();

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

                stopBattle();

                return;
            }

        } while (time < 10);

        stopBattle();
    }
}
