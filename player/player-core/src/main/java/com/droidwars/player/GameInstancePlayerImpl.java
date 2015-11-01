package com.droidwars.player;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.droidwars.game.GameInstance;
import com.droidwars.game.factory.BattleRecordShipFactory;
import com.droidwars.game.generator.SimpleIdGenerator;
import com.droidwars.game.objects.ships.Ship;
import com.droidwars.game.record.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

@Slf4j
public class GameInstancePlayerImpl implements GameInstance {

    @Getter
    private final SimpleIdGenerator idGenerator = new SimpleIdGenerator();
    @Getter
    private final GameRecordWriter gameRecordWriter = new GameRecordWriterNullImpl();
    private final GameRecordReader gameRecordReader = new JavaSerializationGameRecordReaderImpl();
    private final BattleRecordShipFactory shipFactory = new BattleRecordShipFactory(this);
    private BattleRecord battleRecord;
    private Map<Long, Ship> shipMap;
    private float time = 0f;

    public void startNewSimulation() {
        try {
            battleRecord = gameRecordReader.read(new FileInputStream("temp.out"));

            shipMap = shipFactory.getShips(battleRecord);

        } catch (FileNotFoundException e) {
            log.error("Ошибка открытия файла записи боя", e);
        }
    }

    public void updateAll(float delta) {
        StepRecord stepRecord = battleRecord.getRecordList().poll();

        if (stepRecord != null) {

        } else {
            stopSimulation();
        }
    }

    private void stopSimulation() {
        //TODO
    }

    public void renderAll(Batch gameBatch) {

        /*// Рендерим корабли
        for (Ship ship : GameInstance.getInstance().ships) {
            if (ship.alive) {
                ship.draw(gameBatch);
            } else {
                gameOver = true;
//                GameInstance.getInstance().ships.removeValue(ship, true);
            }
        }

        // Рендерим снаряды
        for (Projectile projectile : GameInstance.getInstance().projectiles) {
            if (projectile.alive) {
                projectile.draw(gameBatch);
            } else {
                GameInstance.getInstance().projectiles.removeValue(projectile, true);
            }
        }*/
    }

}
