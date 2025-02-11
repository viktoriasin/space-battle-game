package ru.sinvic.server.commands;

public class RepeatTwiceCommand extends RepeatCommand {
    public RepeatTwiceCommand(Command command) {
        super(command);
    }

    @Override
    public Command getInnerCommand() {
        return command;
    }
}
