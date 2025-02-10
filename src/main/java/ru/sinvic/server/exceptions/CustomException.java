package ru.sinvic.server.exceptions;

import ru.sinvic.server.CommandLooper;
import ru.sinvic.server.commands.Command;

import java.util.Queue;

public class CustomException extends Exception {
    private final CommandLooper looper;

    public CustomException (String message, CommandLooper looper) {
        super(message);
        this.looper = looper;
    }

    public CommandLooper getCommandLooper() {
        return looper;
    }
}
