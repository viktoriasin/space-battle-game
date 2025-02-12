package ru.sinvic.server;

import ru.sinvic.server.commands.Command;
import ru.sinvic.server.commands.RotateCommand;
import ru.sinvic.server.exceptions.ExceptionHandlerChooser;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Queue;

public class CommandLooper {
    private final Queue<Command> commands = new ArrayDeque<>();
    // TODO: развязать циклическую зависимость между handler и looper
    private final ExceptionHandlerChooser exceptionHandlerChooser;

    public CommandLooper(ExceptionHandlerChooser exceptionHandlerChooser) {
        this.exceptionHandlerChooser = exceptionHandlerChooser;
    }

    public void schedule(Command... commands) {
        Collections.addAll(this.commands, commands);
    }

    public void loop() {
        while (true) {
            Command command = commands.poll();
            if (command == null) {
                break;
            }
            try {
                command.execute();
            } catch (Exception e) {
                exceptionHandlerChooser
                        .chooseExceptionHandler(command.getClass(), e.getClass())
                        .handleException(command, e);
            }
        }
    }

    public static void main(String[] args) {
        CommandLooper commandLooper = new CommandLooper(new ExceptionHandlerChooser());
//        // проверка, что repeatTwice вызовет обработчик логирования исключения
//        commandLooper.schedule(new RepeatTwiceCommand(new MoveCommand()));
//        commandLooper.loop();

        // проверка последовательности команд - повторить, затем логировать
        commandLooper.schedule(new RotateCommand());
        commandLooper.loop();
    }
}
