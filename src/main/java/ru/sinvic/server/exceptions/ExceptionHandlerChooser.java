package ru.sinvic.server.exceptions;

import ru.sinvic.server.CommandLooper;
import ru.sinvic.server.commands.*;
import ru.sinvic.server.exceptions.handlers.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ExceptionHandlerChooser {

    private final Map<Class<? extends Command>, Map<Class<? extends Exception>, ExceptionHandler>> store = new HashMap<>();

    public void init(CommandLooper commandLooper) {
        Map<Class<? extends Exception>, ExceptionHandler> moveCommandMap = new HashMap<>();
        moveCommandMap.put(NullPointerException.class, new RetryTwiceCommandExceptionHandler(commandLooper));
        store.put(MoveCommand.class, moveCommandMap);

        Map<Class<? extends Exception>, ExceptionHandler> retryTwiceCommandMap = new HashMap<>();
        retryTwiceCommandMap.put(NullPointerException.class, new RetryOnceCommandExceptionHandler(commandLooper));
        store.put(RetryTwiceCommand.class, retryTwiceCommandMap);

        Map<Class<? extends Exception>, ExceptionHandler> repeatCommandMap = new HashMap<>();
        repeatCommandMap.put(NullPointerException.class, new LogCommandExceptionHandler(commandLooper));
        store.put(RetryOnceCommand.class, repeatCommandMap);
    }

    public ExceptionHandler chooseExceptionHandler(Class<? extends Command> commandClass, Class<? extends Exception> exceptionClass) {
        return store.getOrDefault(commandClass, Collections.emptyMap())
                .getOrDefault(exceptionClass, new DefaultExceptionHandler());
    }
}
