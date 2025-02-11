package ru.sinvic.server.commands;

public class RepeatCommand implements Command {
    protected final Command command;

    public RepeatCommand(Command command) {
        this.command = command;
    }

    @Override
    public void execute() {
        command.execute();
    }
}
