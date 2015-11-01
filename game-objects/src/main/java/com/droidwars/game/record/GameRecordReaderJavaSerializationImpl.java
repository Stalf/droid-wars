package com.droidwars.game.record;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;


/**
 * Простая реализация на основе встроенного в Java сериализатора объектов.
 * Неоптимальная и требует доработки!
 */
@Slf4j
public class GameRecordReaderJavaSerializationImpl implements GameRecordReader {

    @Override
    public BattleRecord read(InputStream inputStream) {

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            return (BattleRecord) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            log.error("Ошибка при чтении боя из потока", e);
        }

        return null;
    }
}
