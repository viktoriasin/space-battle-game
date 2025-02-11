package ru.sinvic.server.exceptions;

import ru.sinvic.server.commands.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class ExceptionHandler {
    public Map<Class<? extends Command>, Map<Class<? extends Exception>, BiFunction<? super Command, ? super ExceptionWithLooper, ? extends Command>>> store = new HashMap<>();

    {
        Map<Class<? extends Exception>, BiFunction<? super Command, ? super ExceptionWithLooper, ? extends Command>> m = new HashMap<>();
        m.put(NullPointerException.class, LogExceptionHandlerCommand::new);
        m.put(IllegalArgumentException.class, RepeatExceptionHandlerCommand::new);
        store.put(MoveCommand.class, m);

        Map<Class<? extends Exception>, BiFunction<? super Command, ? super ExceptionWithLooper, ? extends Command>> n = new HashMap<>();
        n.put(NullPointerException.class, RepeatTwiceExceptionHandlerCommand::new);
        store.put(RotateCommand.class, n);

        Map<Class<? extends Exception>, BiFunction<? super Command, ? super ExceptionWithLooper, ? extends Command>> l = new HashMap<>();
        l.put(NullPointerException.class, LogAfterRepeatExceptionHandlerCommand::new);
        store.put(RepeatTwiceCommand.class, l);
    }

    public Command handle(Command command, ExceptionWithLooper exceptionWithLooper) {

        return store.getOrDefault(command.getClass(), Collections.emptyMap())
                .getOrDefault(exceptionWithLooper.getCause().getClass(), (cmd, e) -> {throw new RuntimeException("No handler for " + cmd.getClass() + " and " + e.getClass());})
                .apply(command, exceptionWithLooper);
    }
}
