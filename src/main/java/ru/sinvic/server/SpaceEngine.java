package ru.sinvic.server;

import ru.sinvic.server.commands.MoveCommand;
import ru.sinvic.server.exceptions.ExceptionHandlerChooser;

public class SpaceEngine {
    public final ExceptionHandlerChooser exceptionHandlerChooser;
    public final CommandLooper commandLooper;

    public SpaceEngine() {
        exceptionHandlerChooser = new ExceptionHandlerChooser();
        commandLooper = new CommandLooper(exceptionHandlerChooser);
        exceptionHandlerChooser.init(commandLooper);
    }

    public static void main(String[] args) {
        SpaceEngine spaceEngine = new SpaceEngine();
        spaceEngine.commandLooper.schedule(new MoveCommand());
        spaceEngine.commandLooper.loop();
    }
}
