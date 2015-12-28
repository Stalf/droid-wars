package com.droidwars.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.droidwars.game.GameInstance;
import com.droidwars.game.command.Command;
import com.droidwars.game.command.CommandType;
import com.droidwars.game.factory.BattleRecordShipFactory;
import com.droidwars.game.generator.SimpleIdGenerator;
import com.droidwars.game.objects.ships.Ship;
import com.droidwars.game.record.*;
import com.droidwars.player.factory.GameObjectSpriteFactory;
import com.droidwars.player.objects.ships.ShipSprite;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class GameInstancePlayerImpl implements GameInstance {

    @Getter
    private final SimpleIdGenerator idGenerator = new SimpleIdGenerator();
    @Getter
    private final GameRecordWriter gameRecordWriter = new GameRecordWriterNullImpl();
    private final GameRecordReader gameRecordReader = new JavaSerializationGameRecordReaderImpl();
    private final BattleRecordShipFactory shipFactory = new BattleRecordShipFactory(this);
    private final GameObjectSpriteFactory shipSpriteFactory = new GameObjectSpriteFactory(this);
    private BattleRecord battleRecord;
    // Список для связи gameId кораблей из записи боя и экземпляров кораблей в клиенте
    private Map<Long, Ship> recordShipMap;

    // Список кораблей в бою
    @Getter
    private List<ShipSprite> shipSpriteList = Lists.newLinkedList();
    @Getter
    private float gameTime = 0f;
    @Getter
    private float playerTime = 0f;
    private float timeFromLastStep = 0f;

    private static GameInstancePlayerImpl instance;

    public static GameInstancePlayerImpl getInstance() {
        if (instance == null) {
            instance = new GameInstancePlayerImpl();
        }
        return instance;
    }

    /**
     * Загружает информацию о бое и запускает отрисовку
     */
    public void startNewSimulation() {
        try {
            battleRecord = gameRecordReader.read(new FileInputStream("c:/work/droid-wars/tmp/temp.out"));

            recordShipMap = shipFactory.getShips(battleRecord);

            shipSpriteList.addAll(shipSpriteFactory.getShipSprites(recordShipMap.values()));

        } catch (FileNotFoundException e) {
            log.error("Ошибка открытия файла записи боя", e);
        }
    }

    /**
     * Метод выполняет команды кораблям, сохраненные в записи о бое.
     * Автоматически синхронизирует время клиента с тактами серверного движка игры
     * @param delta время с прошлого такта отрисовки
     */
    protected void updateAll(float delta) {
        StepRecord stepRecord = battleRecord.getRecordList().peek();

        playerTime += delta;
        timeFromLastStep += delta;

        if (stepRecord != null) {
            float engineDelta = stepRecord.getDelta();
            // Синхронизируем шаги движка с тактами отрисовки клиента
            if (timeFromLastStep >= engineDelta) {
                gameTime += engineDelta;

                stepRecord = battleRecord.getRecordList().poll();

                HashMap<Long, EnumMap<CommandType, Command<Ship>>> commands = stepRecord.getShipCommands();
                for (Map.Entry<Long, EnumMap<CommandType, Command<Ship>>> shipCommand : commands.entrySet()) {
                    EnumMap<CommandType, Command<Ship>> shipCommandsMap = shipCommand.getValue();
                    Ship ship = recordShipMap.get(shipCommand.getKey());

                    ship.command(shipCommandsMap);

                    ship.update(engineDelta);
                }
                timeFromLastStep = 0f;
            }
        } else {
            stopSimulation();
        }
    }

    private void stopSimulation() {
        Gdx.app.exit();
    }

    /**
     * Основной цикл отрисовки клиента. Внутри вызывает обновление состояния всех объектов и отправку им команд.
     * @param delta время с прошлого такта отрисовки
     * @param gameBatch пакет для отрисовки игровых объектов
     */
    public void renderAll(float delta, Batch gameBatch) {

        updateAll(delta);

        // Рендерим корабли
        for (ShipSprite ship : shipSpriteList) {
            if (ship.getSubject().isAlive()) {
                ship.draw(gameBatch);
            }
        }
/*
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
