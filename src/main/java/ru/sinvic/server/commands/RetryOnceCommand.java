package ru.sinvic.server.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RetryOnceCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(RetryOnceCommand.class);

    protected final Command command;

    public RetryOnceCommand(Command command) {
        this.command = command;
    }

    @Override
    public void execute() {
        logger.info("Start executing {}", command.getClass().getSimpleName());
        command.execute();
    }
}
