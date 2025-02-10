package ru.sinvic.server.commands;

import ru.sinvic.server.exceptions.CustomException;

public class RepeatTwiceExceptionHandlerCommand implements Command {
    private final Command command;
    private final CustomException exception;

    public RepeatTwiceExceptionHandlerCommand(Command command, CustomException exception) {
        this.command = command;
        this.exception = exception;
    }

    @Override
    public void execute() {
        exception.getCommandLooper().schedule(new RepeatTwiceExceptionCommand(command));
    }
}
