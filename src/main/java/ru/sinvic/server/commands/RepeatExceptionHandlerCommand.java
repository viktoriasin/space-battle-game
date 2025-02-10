package ru.sinvic.server.commands;

import ru.sinvic.server.exceptions.CustomException;

public class RepeatExceptionHandlerCommand implements Command {
    private final Command command;
    private final CustomException exception;

    public RepeatExceptionHandlerCommand(Command command, CustomException exception) {
        this.command = command;
        this.exception = exception;
    }
    @Override
    public void execute() {
        exception.getCommandLooper().schedule(new RepeatExceptionHandlerCommand(command, exception));
    }
}
