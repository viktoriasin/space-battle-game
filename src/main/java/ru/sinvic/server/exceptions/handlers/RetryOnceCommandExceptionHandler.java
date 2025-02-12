package ru.sinvic.server.exceptions.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sinvic.server.CommandLooper;
import ru.sinvic.server.commands.Command;
import ru.sinvic.server.commands.RetryOnceCommand;

public class RetryOnceCommandExceptionHandler implements ExceptionHandler {


    private static final Logger logger = LoggerFactory.getLogger(RetryOnceCommandExceptionHandler.class);
    private final CommandLooper looper;

    public RetryOnceCommandExceptionHandler(CommandLooper looper) {
        this.looper = looper;
    }

    @Override
    public void handleException(Command command, Exception exception) {
        logger.info("Handle {} for {}", exception.getClass().getSimpleName(), command.getClass().getSimpleName());
        looper.schedule(new RetryOnceCommand(command));
    }
}
