package ru.sinvic.server.exceptions.handlers;

import ru.sinvic.server.commands.Command;

public class DefaultExceptionHandler implements ExceptionHandler {
    @Override
    public void handleException(Command command, Exception exception) {
        throw new RuntimeException("No handler for " + command.getClass() + " and " + exception.getClass());
    }
}
