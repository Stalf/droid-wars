package com.droidwars.game.record;

import com.droidwars.game.command.Command;
import com.droidwars.game.objects.GameObject;
import com.droidwars.game.objects.ships.Ship;

import java.io.OutputStream;
import java.util.List;

/**
 * Интерфейс для записи хода боя
 */
public interface GameRecordWriter {

    /**
     * Начало записи боя. Сохраняет исходные параметры (расположение, типы кораблей)
     * @param ships - список кораблей в состояниях на начало боя
     */
    void startRecord(List<Ship> ships);

    /**
     * Сохраняет команду игровому объекту
     * @param subject - объект (корабль, ракета, и т.д.)
     * @param command - команда
     */
    void write(GameObject subject, Command command);

    /**
     * Начинает запись нового игрового такта
     * @param delta - длина такта
     */
    void stepBegin(float delta);

    /**
     * Завершает запись игрового такта
     */
    void stepEnd();

    /**
     * Останавливает запись боя. Сохраняет результаты в переданный поток
     * @param outputStream поток для сохранения результатов боя
     * @return true - если запись выполнена успешно
     */
    boolean stopRecord(OutputStream outputStream);

}
