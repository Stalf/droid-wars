package com.droidwars.game.exceptions;

/**
 * Root Exception class for all ingame exceptions
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
