package ru.sinvic.server.commands.exception;

import ru.sinvic.server.commands.Command;
import ru.sinvic.server.exceptions.ExceptionHandler;

public class RepeatExceptionCommandCommand implements Command {
    private final Command command;
    private ExceptionHandler exceptionHandler;


    public RepeatExceptionCommandCommand(Command command, ExceptionHandler exceptionHandler) {
        this.command = command;
        this.exceptionHandler = exceptionHandler;
    }

    @Override
    public void execute() {
        try {
            command.execute();
        } catch (Exception e) {
            exceptionHandler.handle(command, e).execute();
        }
    }
}
