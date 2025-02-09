//package ru.sinvic.server.commands.exception.macro;
//
//import ru.sinvic.server.commands.Command;
//import ru.sinvic.server.commands.exception.LogExceptionCommand;
//import ru.sinvic.server.commands.exception.RepeatExceptionCommandCommand;
//
//public class RepeatThenLogExceptionCommand implements MacroCommand {
//    private final Command command;
//    private final Exception exception;
//
//    public RepeatThenLogExceptionCommand(Command command, Exception exception) {
//        this.command = command;
//        this.exception = exception;
//    }
//
//    @Override
//    public void execute() {
//        try {
//            new RepeatExceptionCommandCommand(command).execute();
//        } catch (Exception e) {
//            new LogExceptionCommand(command, exception).execute();
//        }
//    }
//}
