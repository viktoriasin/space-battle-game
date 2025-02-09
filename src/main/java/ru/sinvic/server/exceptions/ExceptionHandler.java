package ru.sinvic.server.exceptions;

import ru.sinvic.server.commands.Command;
import ru.sinvic.server.commands.LogExceptionCommand;
import ru.sinvic.server.commands.MoveCommand;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class ExceptionHandler {
    public Map<Class<? extends Command>, Map<Class<? extends Exception>, BiFunction<? super Command, ? super Exception, ? extends Command>>> store = new HashMap<>();

    {
        Map<Class<? extends Exception>, BiFunction<? super Command, ? super Exception, ? extends Command>> m = new HashMap<>();
        m.put(NullPointerException.class, LogExceptionCommand::new);
        store.put(MoveCommand.class, m);
    }

    public Command handle(Command command, Exception exception) {

        return store.getOrDefault(command.getClass(), Collections.emptyMap())
                .getOrDefault(exception.getClass(), (cmd, e) -> {throw new RuntimeException("No handler for " + cmd.getClass() + " and " + e.getClass());})
                .apply(command, exception);
    }
}
