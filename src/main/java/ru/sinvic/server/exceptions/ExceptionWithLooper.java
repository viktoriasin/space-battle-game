package ru.sinvic.server.exceptions;

import ru.sinvic.server.CommandLooper;

public class ExceptionWithLooper extends RuntimeException {
    private final CommandLooper looper;

    public ExceptionWithLooper(Throwable cause, CommandLooper looper) {
        super(cause);
        this.looper = looper;
    }

    public CommandLooper getCommandLooper() {
        return looper;
    }
}
