package com.droidwars.game.generator;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Простой генератор идентификаторов
 */
public class SimpleIdGenerator implements IdGenerator{
    private AtomicLong id = new AtomicLong(0L);

    public long getNextId() {
        return id.incrementAndGet();
    }
}
