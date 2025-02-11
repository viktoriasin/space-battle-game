package ru.sinvic.server;

import ru.sinvic.server.commands.Command;
import ru.sinvic.server.commands.MoveCommand;
import ru.sinvic.server.commands.RepeatTwiceCommand;
import ru.sinvic.server.commands.RotateCommand;
import ru.sinvic.server.exceptions.ExceptionWithLooper;
import ru.sinvic.server.exceptions.ExceptionHandler;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Queue;

public class CommandLooper {
    private final Queue<Command> commands = new ArrayDeque<>();
    private final ExceptionHandler exceptionHandler;

    public CommandLooper(ExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
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
                exceptionHandler.handle(command, new ExceptionWithLooper(e, this)).execute();
            }
        }
    }

    public static void main(String[] args) {
        CommandLooper commandLooper = new CommandLooper(new ExceptionHandler());
//        // проверка, что repeatTwice вызовет обработчик логирования исключения
//        commandLooper.schedule(new RepeatTwiceCommand(new MoveCommand()));
//        commandLooper.loop();

        // проверка последовательности команд - повторить, затем логировать
        commandLooper.schedule(new RotateCommand());
        commandLooper.loop();
    }
}
