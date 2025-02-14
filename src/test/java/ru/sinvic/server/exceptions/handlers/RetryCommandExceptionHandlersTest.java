package ru.sinvic.server.exceptions.handlers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sinvic.server.CommandLooper;
import ru.sinvic.server.commands.Command;
import ru.sinvic.server.commands.RetryOnceCommand;
import ru.sinvic.server.commands.RetryTwiceCommand;

@ExtendWith(MockitoExtension.class)
class RetryCommandExceptionHandlersTest {
    @Mock
    CommandLooper commandLooper;
    @Mock
    Command command;
    @Mock
    Exception exception;

    @Test
    public void testRetryOnceCommandExceptionHandler() {
        RetryOnceCommandExceptionHandler logCommandExceptionHandler = new RetryOnceCommandExceptionHandler(commandLooper);
        logCommandExceptionHandler.handleException(command, exception);

        Mockito.verify(commandLooper, Mockito.times(1)).schedule(ArgumentMatchers.isA(RetryOnceCommand.class));
    }

    @Test
    public void testRetryTwiceCommandExceptionHandler() {
        RetryTwiceCommandExceptionHandler logCommandExceptionHandler = new RetryTwiceCommandExceptionHandler(commandLooper);
        logCommandExceptionHandler.handleException(command, exception);

        Mockito.verify(commandLooper, Mockito.times(1)).schedule(ArgumentMatchers.isA(RetryTwiceCommand.class));
    }
}