package com.droidwars.game;

import com.droidwars.game.command.Command;
import com.droidwars.game.objects.GameObject;
import com.droidwars.game.objects.ships.Ship;
import com.droidwars.game.record.GameRecordWriter;

import java.io.OutputStream;
import java.util.List;

public class GameRecordWriterNullTestImpl implements GameRecordWriter {
    @Override
    public void startRecord(List<Ship> ships) {

    }

    @Override
    public void write(GameObject subject, Command command) {

    }

    @Override
    public void stepBegin(float delta) {

    }

    @Override
    public void stepEnd() {

    }

    @Override
    public boolean stopRecord(OutputStream outputStream) {
        return false;
    }

}
