package ru.sinvic.server.commands;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class RetryCommandsTest {
    @Mock
    Command command;


    @Test
    public void testRetryOnceCommand() {
        RetryOnceCommand retryOnceCommand = new RetryOnceCommand(command);
        retryOnceCommand.execute();

        Mockito.verify(command, times(1)).execute();
    }

    @Test
    public void testRetryTwiceCommand() {
        RetryTwiceCommand retryOnceCommand = new RetryTwiceCommand(command);
        retryOnceCommand.execute();

        Mockito.verify(command, times(1)).execute();
    }
}