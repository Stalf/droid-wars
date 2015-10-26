package com.droidwars.game.exceptions;

/**
 * Корневой класс для внутриигровых исключений
 */
public class GameException extends Exception{

    public GameException(String s) {
        super(s);
    }
}
