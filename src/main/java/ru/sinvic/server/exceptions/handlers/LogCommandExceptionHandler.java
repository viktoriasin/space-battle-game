package ru.sinvic.server.exceptions.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sinvic.server.CommandLooper;
import ru.sinvic.server.commands.Command;
import ru.sinvic.server.commands.LogCommand;

public class LogCommandExceptionHandler implements ExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(LogCommandExceptionHandler.class);

    private final CommandLooper looper;

    public LogCommandExceptionHandler(CommandLooper looper) {
        this.looper = looper;
    }

    @Override
    public void handleException(Command command, Exception exception) {
        logger.info("Handle {} for {}", exception.getClass().getSimpleName(), command.getClass().getSimpleName());
        String message = String.format("Command %s threw error: %s", command.getClass().getSimpleName(), exception.getMessage());
        looper.schedule(new LogCommand(message, true));
    }
}
