package ru.sinvic.server.commands;

import ru.sinvic.server.exceptions.ExceptionWithLooper;

public class LogAfterRepeatExceptionHandlerCommand implements Command {
    private final Command command;
    private final ExceptionWithLooper exception;

    public LogAfterRepeatExceptionHandlerCommand(Command command, ExceptionWithLooper exception) {
        this.command = command;
        this.exception = exception;
    }

    @Override
    public void execute() {
        exception.getCommandLooper().schedule(new LogExceptionCommand(command.getInnerCommand(), exception));
    }
}
