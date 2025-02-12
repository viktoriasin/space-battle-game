package ru.sinvic.server.commands;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RetryOnceCommandTest {

    @Mock
    private Command command;

    @Test
    void testCommand() {
        RetryOnceCommand retryOnceCommand = new RetryOnceCommand(command);
        retryOnceCommand.execute();

        Mockito.verify(command, Mockito.times(1)).execute();
    }

    @Test
    void testRepeatCommand() {
        RepeatTwiceCommand repeatTwiceCommand = new RepeatTwiceCommand(command);
        repeatTwiceCommand.execute();

        Mockito.verify(command, Mockito.times(1)).execute();
    }
}