package com.droidwars.game.engine;

import com.droidwars.game.GameInstance;
import com.droidwars.game.command.ship.TurnDirectionCommand;
import com.droidwars.game.engine.factory.AbstractShipFactory;
import com.droidwars.game.engine.factory.RandomPositionShipFactory;
import com.droidwars.game.engine.utils.Constants;
import com.droidwars.game.generator.SimpleIdGenerator;
import com.droidwars.game.objects.ships.Ship;
import com.droidwars.game.objects.ships.ShipType;
import com.droidwars.game.record.GameRecordWriter;
import com.droidwars.game.record.JavaSerializationGameRecordWriterImpl;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * Для каждого боя формируется один экземпляр этого класса.
 * Реализует основной жизненный цикл игры.
 */
@Slf4j
public class GameInstanceServerImpl implements Runnable, GameInstance {

    @Getter
    private final SimpleIdGenerator idGenerator = new SimpleIdGenerator();
    @Getter
    private final GameRecordWriter gameRecordWriter = new JavaSerializationGameRecordWriterImpl();

    private OutputStream battleLogFile;

    @Getter
    private List<Ship> shipList = new LinkedList<>();
    private AbstractShipFactory shipFactory = new RandomPositionShipFactory(this);
    @Getter
    private float time = 0f;

    public GameInstanceServerImpl() {
        try {
            battleLogFile = new FileOutputStream("temp.out");
        } catch (FileNotFoundException e) {
            log.error("Ошибка открытия файла записи лога боя", e);
        }
    }

    /**
     * Инициализирует бой. Расставляет объекты
     */
    public void startBattle() {

        log.debug("Battle start");

        time = 0f;

        shipList.add(shipFactory.getShip(ShipType.FRIGATE));
        shipList.add(shipFactory.getShip(ShipType.FRIGATE));

        gameRecordWriter.startRecord(shipList);
    }

    public void stopBattle() {
        gameRecordWriter.stopRecord(battleLogFile);
    }

    public void update(float delta) {
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
