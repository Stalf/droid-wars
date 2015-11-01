package com.droidwars.game.exceptions;

/**
 * Корневой класс для внутриигровых исключений
 */
public class GameException extends Exception{

    public GameException(String s) {
        super(s);
    }

    public GameException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public GameException(Throwable throwable) {
        super(throwable);
    }
}
