package ru.sinvic.server.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogExceptionCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(LogExceptionCommand.class);

    private final Command command;
    private final Exception exception;

    public LogExceptionCommand(Command command, Exception exception) {
        this.command = command;
        this.exception = exception;
    }

    @Override
    public void execute() {
        logger.error("Command {} threw error: {}", command.getClass().getName(), exception.getMessage());
    }
}
