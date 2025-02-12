package ru.sinvic.server.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sinvic.server.CommandLooper;
import ru.sinvic.server.exceptions.ExceptionWithLooper;
import ru.sinvic.server.exceptions.handlers.LogExceptionHandler;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExceptionHandlersCommandTest {

    @Mock
    private Command command;

    @Mock
    private ExceptionWithLooper exception;
    @Mock
    private CommandLooper commandLooper;

    @BeforeEach
    public void setUp() {
        when(exception.getCommandLooper()).thenReturn(commandLooper);
    }

    @Test
    public void testLogExceptionHandler() {
        LogExceptionHandlerCommand logExceptionHandlerCommand = new LogExceptionHandlerCommand(command, exception);
        logExceptionHandlerCommand.execute();
        verify(exception, times(1)).getCommandLooper();
        verify(commandLooper, times(1)).schedule(ArgumentMatchers.isA(LogExceptionHandler.class));
    }

    @Test
    public void testRepeatCommandExceptionHandler() {
        RepeatExceptionHandlerCommand repeatExceptionHandlerCommand = new RepeatExceptionHandlerCommand(command, exception);
        repeatExceptionHandlerCommand.execute();
        verify(exception, times(1)).getCommandLooper();
        verify(commandLooper, times(1)).schedule(ArgumentMatchers.isA(RetryOnceCommand.class));
    }

    @Test
    public void testRepeatTwiceExceptionHandler() {
        RepeatTwiceExceptionHandlerCommand repeatTwiceExceptionHandlerCommand = new RepeatTwiceExceptionHandlerCommand(command, exception);
        repeatTwiceExceptionHandlerCommand.execute();
        verify(exception, times(1)).getCommandLooper();
        verify(commandLooper, times(1)).schedule(ArgumentMatchers.isA(RepeatTwiceCommand.class));
    }

    @Test
    public void testLogAfterRepeatException() {
        LogAfterRepeatExceptionHandlerCommand logAfterRepeatExceptionHandlerCommand = new LogAfterRepeatExceptionHandlerCommand(command, exception);
        logAfterRepeatExceptionHandlerCommand.execute();
        verify(exception, times(1)).getCommandLooper();
        verify(commandLooper, times(1)).schedule(ArgumentMatchers.isA(LogExceptionHandler.class));
    }
}