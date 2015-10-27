package com.droidwars.game.command;

import com.droidwars.game.objects.GameObject;
import com.google.common.collect.Maps;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.EnumMap;
import java.util.Map;

/**
 * Класс принимает и исполняет команды
 */
@Slf4j
@RequiredArgsConstructor
public class CommandExecutorImpl<T extends GameObject> implements CommandExecutor<T> {

    @NonNull
    private T subject;

    private EnumMap<CommandType, Command<T>> commands = Maps.newEnumMap(CommandType.class);

    @Override
    public void add(Command<T> command) {

        if (log.isTraceEnabled()) {
            log.trace("Add command {}", command.getType().name());
        }

        commands.put(command.getType(), command);
    }

    @Override
    public EnumMap<CommandType, Command<T>> getAll() {
        return commands;
    }

    @Override
    public void clear() {
        if (log.isTraceEnabled()) {
            log.trace("Clear command");
        }

        commands.clear();
    }

    @Override
    public Command remove(CommandType commandType) {

        if (log.isTraceEnabled()) {
            log.trace("Remove command {}", commandType.name());
        }

        return commands.remove(commandType);
    }

    @Override
    public void execute(float delta) {

        if (!subject.isAlive()) {
            return;
        }

        for (Map.Entry<CommandType, Command<T>> next : commands.entrySet()) {
            Command command = next.getValue();

            if (log.isTraceEnabled()) {
                log.trace("Executing command {}", next.getKey().name());
            }

            command.execute(subject, delta);
        }

        this.clear();
    }
}
