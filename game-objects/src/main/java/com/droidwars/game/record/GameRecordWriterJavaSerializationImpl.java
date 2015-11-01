package com.droidwars.game.record;

import com.droidwars.game.command.Command;
import com.droidwars.game.objects.GameObject;
import com.droidwars.game.objects.ships.Ship;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Реализация сохранения записи боя с помощью встроенного в Java сериализатора объектов
 * Неоптимальная и требует доработки!
 */
@Slf4j
public class GameRecordWriterJavaSerializationImpl implements GameRecordWriter {

    private BattleRecord battleRecord;
    private StepRecord tmpStep = null;
    private float time = 0f;

    @Override
    public void startRecord(List<Ship> ships) {

        if (log.isTraceEnabled()) {
            log.trace("Start battle recording. Ships: {}", ships);
        }

        battleRecord = new BattleRecord(ships);
    }

    @Override
    public void write(GameObject subject, Command command) {
        if (tmpStep == null) {
            throw new IllegalStateException("Метод write вызван до метода stepBegin");
        }

        tmpStep.record(subject, command);
    }

    @Override
    public void stepBegin(float delta) {
        if (tmpStep != null) {
            throw new IllegalStateException("Метод stepBegin вызван дважды, без вызова stepEnd");
        }
        time += delta;
        tmpStep = new StepRecord(delta);
    }

    @Override
    public void stepEnd() {
        if (tmpStep == null) {
            throw new IllegalStateException("Метод stepEnd вызван до метода stepBegin");
        }
        battleRecord.add(tmpStep);
        tmpStep = null;
    }

    @Override
    public boolean stopRecord(OutputStream outputStream) {
        if (log.isTraceEnabled()) {
            log.trace("Stop battle recording");
        }

        try {

            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
                objectOutputStream.writeObject(battleRecord);
                objectOutputStream.flush();
            }

            return true;

        } catch (IOException e) {
            log.error("Ошибка при записи боя в поток", e);
        } finally {
            battleRecord = null;
        }

        return false;
    }
}
