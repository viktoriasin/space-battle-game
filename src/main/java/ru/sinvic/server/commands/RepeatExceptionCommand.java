package ru.sinvic.server.commands;

public class RepeatExceptionCommand implements Command {
    private final Command command;

    public RepeatExceptionCommand(Command command) {
        this.command = command;
    }

    @Override
    public void execute() {
        command.execute();
    }
}
