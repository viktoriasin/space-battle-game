package ru.sinvic.server.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(LogCommand.class);

    private final String message;
    private final boolean isError;

    public LogCommand(String message, boolean isError) {
        this.message = message;
        this.isError = isError;
    }

    public LogCommand(String message) {
        this(message, false);
    }

    @Override
    public void execute() {
        if (isError) {
            logger.error(message);
        } else {
            logger.info(message);
        }
    }
}
