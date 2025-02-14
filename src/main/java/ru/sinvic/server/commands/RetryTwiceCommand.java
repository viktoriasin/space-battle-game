package ru.sinvic.server.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RetryTwiceCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(RetryTwiceCommand.class);

    protected final Command command;

    public RetryTwiceCommand(Command command) {
        this.command = command;
    }

    @Override
    public void execute() {
        logger.info("Start executing {}", command.getClass().getSimpleName());
        command.execute();
    }
}
