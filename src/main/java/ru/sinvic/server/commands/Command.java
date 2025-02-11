package ru.sinvic.server.commands;

public interface Command {
    void execute();

    default Command getInnerCommand() {
        return this;
    }
}
