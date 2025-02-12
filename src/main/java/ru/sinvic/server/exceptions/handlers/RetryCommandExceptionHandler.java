package ru.sinvic.server.exceptions.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sinvic.server.CommandLooper;
import ru.sinvic.server.commands.Command;
import ru.sinvic.server.commands.RepeatCommand;

public class RetryCommandExceptionHandler implements ExceptionHandler {


    private static final Logger logger = LoggerFactory.getLogger(RetryCommandExceptionHandler.class);
    private final CommandLooper looper;

    public RetryCommandExceptionHandler(CommandLooper looper) {
        this.looper = looper;
    }

    @Override
    public void handleException(Command command, Exception exception) {
        logger.info("Handle {} for {}", exception.getClass().getSimpleName(), command.getClass().getSimpleName());
        looper.schedule(new RepeatCommand(command, 1));
    }
}
