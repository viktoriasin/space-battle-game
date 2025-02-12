package ru.sinvic.server.exceptions;

import ru.sinvic.server.CommandLooper;
import ru.sinvic.server.commands.*;
import ru.sinvic.server.exceptions.handlers.DefaultExceptionHandler;
import ru.sinvic.server.exceptions.handlers.ExceptionHandler;
import ru.sinvic.server.exceptions.handlers.LogCommandExceptionHandler;
import ru.sinvic.server.exceptions.handlers.RetryCommandExceptionHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ExceptionHandlerChooser {

    private final Map<Class<? extends Command>, Map<Class<? extends Exception>, ExceptionHandler>> store = new HashMap<>();

    public void init(CommandLooper commandLooper) {
        Map<Class<? extends Exception>, ExceptionHandler> moveCommandMap = new HashMap<>();
        moveCommandMap.put(NullPointerException.class, new RetryCommandExceptionHandler(commandLooper));
        store.put(MoveCommand.class, moveCommandMap);

        Map<Class<? extends Exception>, ExceptionHandler> repeatCommandMap = new HashMap<>();
        repeatCommandMap.put(NullPointerException.class, new LogCommandExceptionHandler(commandLooper));
        store.put(RepeatCommand.class, repeatCommandMap);

//
//        var rotateCommandMap = createHashMap();
//        rotateCommandMap.put(NullPointerException.class, RepeatTwiceExceptionHandlerCommand::new);
//        store.put(RotateCommand.class, rotateCommandMap);
//
//        var repeatTwiceCommandMap = createHashMap();
//        repeatTwiceCommandMap.put(NullPointerException.class, LogAfterRepeatExceptionHandlerCommand::new);
//        store.put(RepeatTwiceCommand.class, repeatTwiceCommandMap);
    }

    public ExceptionHandler chooseExceptionHandler(Class<? extends Command> commandClass, Class<? extends Exception> exceptionClass) {
        return store.getOrDefault(commandClass, Collections.emptyMap())
                .getOrDefault(exceptionClass, new DefaultExceptionHandler());
    }
}
