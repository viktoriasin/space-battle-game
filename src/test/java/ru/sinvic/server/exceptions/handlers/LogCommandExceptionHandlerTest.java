package ru.sinvic.server.exceptions.handlers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sinvic.server.CommandLooper;
import ru.sinvic.server.commands.Command;
import ru.sinvic.server.commands.LogCommand;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LogCommandExceptionHandlerTest {
    @Mock
    CommandLooper commandLooper;
    @Mock
    Command command;
    @Mock
    Exception exception;

    @Test
    public void testLogCommandExceptionHandler() {
        LogCommandExceptionHandler logCommandExceptionHandler = new LogCommandExceptionHandler(commandLooper);
        logCommandExceptionHandler.handleException(command, exception);

        Mockito.verify(commandLooper, Mockito.times(1)).schedule(ArgumentMatchers.isA(LogCommand.class));
    }
}