package ru.sinvic.server.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RepeatCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(RepeatCommand.class);

    protected final Command command;
    private final int repeatCount;

    public RepeatCommand(Command command, int repeatCount) {
        if (repeatCount < 0) {
            throw new IllegalArgumentException("Repeat count must not be negative, but given " + repeatCount);
        }
        this.command = command;
        this.repeatCount = repeatCount;
    }

    @Override
    public void execute() {
        logger.info("Start executing {} {} times", command.getClass().getSimpleName(), repeatCount);
        for (int i = 0; i < repeatCount; i++) {
            command.execute();
        }
    }
}
