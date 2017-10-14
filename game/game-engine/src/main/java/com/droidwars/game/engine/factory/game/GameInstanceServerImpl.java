package com.droidwars.game.engine.factory.game;

import com.droidwars.game.command.ship.TurnDirectionCommand;
import com.droidwars.game.engine.GameInstanceServer;
import com.droidwars.game.engine.factory.ship.AbstractShipFactory;
import com.droidwars.game.engine.utils.Constants;
import com.droidwars.game.exceptions.GameException;
import com.droidwars.game.generator.IdGenerator;
import com.droidwars.game.objects.ships.Ship;
import com.droidwars.game.objects.ships.ShipType;
import com.droidwars.game.record.BattleRecordMetadata;
import com.droidwars.game.record.RecordStorage;
import com.droidwars.game.record.RecordWriter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static com.droidwars.game.engine.utils.Constants.BATTLE_TIMEOUT;

/**
 * Для каждого боя формируется один экземпляр этого класса.
 * Реализует основной жизненный цикл игры.
 */
@Slf4j
public class GameInstanceServerImpl implements GameInstanceServer {

    @Getter
    private final IdGenerator idGenerator;
    @Getter
    private final RecordWriter recordWriter;
    @Getter
    private float time = 0f;
    @Getter
    private UUID battleId;

    private final RecordStorage recordStorage;

    @Setter(AccessLevel.PACKAGE)
    private AbstractShipFactory shipFactory;
    private List<Ship> shipList = new LinkedList<>();

    GameInstanceServerImpl(IdGenerator idGenerator, RecordWriter recordWriter, RecordStorage recordStorage) {
        this.idGenerator = idGenerator;
        this.recordWriter = recordWriter;
        this.recordStorage = recordStorage;
    }

    /**
     * Initializes the battle. Creates and places objects
     */
    private BattleRecordMetadata startBattle() throws GameException {
        time = 0f;

        shipList.add(shipFactory.getShip(ShipType.FRIGATE, 1));
        shipList.add(shipFactory.getShip(ShipType.FRIGATE, 2));

        BattleRecordMetadata recordMetadata = recordStorage.createMetadata();

        recordWriter.startRecord(recordMetadata, shipList);

        log.debug("Battle {} started", recordMetadata.getBattleId());
        return recordMetadata;
    }

    private void stopBattle() throws GameException {
        battleId = recordStorage.store(recordWriter.stopRecord());
        log.debug("Battle {} stopped", battleId);
    }

    private void update(float delta) {
        time += delta;

        if (log.isTraceEnabled()) {
            log.trace("Game step, time: {}; delta: {}", time, delta);
        }

        recordWriter.stepBegin(delta);

        for (Ship ship : shipList) {
            ship.command(new TurnDirectionCommand(1));
            ship.update(delta);
        }

        recordWriter.stepEnd();

    }

    @Override
    public void run() {

        log.debug("Game instance thread start");

        try {

            try (BattleRecordMetadata battleRecordMetadata = startBattle()) {
                do {

                    if (!Thread.interrupted()) {
                        update(Constants.DELTA_STEP);
                    } else {
                        log.debug("Game thread interrupted");

                        stopBattle();
                        return;
                    }

                } while (time < BATTLE_TIMEOUT);

                stopBattle();

            } catch (IOException e) {
                throw new GameException(e);
            }
        } catch (GameException e) {
            log.error("Battle execution ended with error", e);
        }

    }
}
