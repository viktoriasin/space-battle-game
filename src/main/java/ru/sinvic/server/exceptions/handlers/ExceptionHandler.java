package ru.sinvic.server.exceptions.handlers;

import ru.sinvic.server.commands.Command;

public interface ExceptionHandler {
    void handleException(Command command, Exception exception);
}
