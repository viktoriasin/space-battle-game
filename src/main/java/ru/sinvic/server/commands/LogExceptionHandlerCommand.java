package ru.sinvic.server.commands;

import ru.sinvic.server.exceptions.ExceptionWithLooper;
import ru.sinvic.server.exceptions.handlers.LogExceptionHandler;

public class LogExceptionHandlerCommand implements Command {
    private final Command command;
    private final ExceptionWithLooper exception;

    public LogExceptionHandlerCommand(Command command, ExceptionWithLooper exception) {
        this.command = command;
        this.exception = exception;
    }

    @Override
    public void execute() {
        exception.getCommandLooper().schedule(new LogExceptionHandler(command, exception));
    }
}
