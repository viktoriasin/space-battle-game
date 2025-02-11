package ru.sinvic.server.commands;

import ru.sinvic.server.exceptions.ExceptionWithLooper;

public class RepeatTwiceExceptionHandlerCommand implements Command {
    private final Command command;
    private final ExceptionWithLooper exception;

    public RepeatTwiceExceptionHandlerCommand(Command command, ExceptionWithLooper exception) {
        this.command = command;
        this.exception = exception;
    }

    @Override
    public void execute() {
        exception.getCommandLooper().schedule(new RepeatTwiceCommand(command));
    }
}
