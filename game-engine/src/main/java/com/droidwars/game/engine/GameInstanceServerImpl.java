package com.droidwars.game.engine;

import com.droidwars.game.GameInstance;
import com.droidwars.game.engine.factory.AbstractShipFactory;
import com.droidwars.game.engine.factory.RandomPositionShipFactory;
import com.droidwars.game.engine.utils.Constants;
import com.droidwars.game.generator.SimpleIdGenerator;
import com.droidwars.game.objects.ships.Ship;
import com.droidwars.game.objects.ships.ShipType;
import com.droidwars.game.record.GameRecordWriter;
import com.droidwars.game.record.GameRecordWriterJavaSerializationImpl;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
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
    private final GameRecordWriter gameRecordWriter = new GameRecordWriterJavaSerializationImpl();

    private OutputStream battleLogFile;

    private List<Ship> shipList = new LinkedList<>();
    private AbstractShipFactory shipFactory = new RandomPositionShipFactory(this);
    private float time = 0f;

    public GameInstanceServerImpl() throws IOException {
        battleLogFile = new FileOutputStream(File.createTempFile("battleLog", ".dwl"));
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
        gameRecordWriter.stopRecord(new ByteArrayOutputStream());
    }

    public void update(float delta) {
        time += delta;

        if (log.isTraceEnabled()) {
            log.trace("Game step, time: {}; delta: {}", time, delta);
        }

        gameRecordWriter.stepBegin(delta);

        for (Ship ship: shipList) {
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
