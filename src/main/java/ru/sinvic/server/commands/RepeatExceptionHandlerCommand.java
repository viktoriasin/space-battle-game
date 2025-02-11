package ru.sinvic.server.commands;

import ru.sinvic.server.exceptions.ExceptionWithLooper;

public class RepeatExceptionHandlerCommand implements Command {
    private final Command command;
    private final ExceptionWithLooper exception;

    public RepeatExceptionHandlerCommand(Command command, ExceptionWithLooper exception) {
        this.command = command;
        this.exception = exception;
    }

    @Override
    public void execute() {
        exception.getCommandLooper().schedule(new RepeatCommand(command));
    }
}
