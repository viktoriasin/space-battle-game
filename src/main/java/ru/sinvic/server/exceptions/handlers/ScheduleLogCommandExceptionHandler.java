package ru.sinvic.server.exceptions.handlers;

import ru.sinvic.server.CommandLooper;
import ru.sinvic.server.commands.Command;
import ru.sinvic.server.commands.LogCommand;

public class ScheduleLogCommandExceptionHandler implements ExceptionHandler {

    private final CommandLooper looper;

    public ScheduleLogCommandExceptionHandler(CommandLooper looper) {
        this.looper = looper;
    }

    @Override
    public void handleException(Command command, Exception exception) {
        looper.schedule(new LogCommand(String.format("Command %s threw error: %s", command.getClass().getName(), exception.getMessage())));
    }
}
