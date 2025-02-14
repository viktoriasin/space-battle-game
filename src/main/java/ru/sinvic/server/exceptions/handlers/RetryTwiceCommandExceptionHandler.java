package ru.sinvic.server.exceptions.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sinvic.server.CommandLooper;
import ru.sinvic.server.commands.Command;
import ru.sinvic.server.commands.RetryOnceCommand;
import ru.sinvic.server.commands.RetryTwiceCommand;

public class RetryTwiceCommandExceptionHandler implements ExceptionHandler {


    private static final Logger logger = LoggerFactory.getLogger(RetryTwiceCommandExceptionHandler.class);
    private final CommandLooper looper;

    public RetryTwiceCommandExceptionHandler(CommandLooper looper) {
        this.looper = looper;
    }

    @Override
    public void handleException(Command command, Exception exception) {
        logger.info("Handle {} for {}", exception.getClass().getSimpleName(), command.getClass().getSimpleName());
        looper.schedule(new RetryTwiceCommand(command));
    }
}
