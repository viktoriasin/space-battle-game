package ru.sinvic.server.commands;

import ch.qos.logback.classic.spi.LoggingEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RepeatCommandTest {

    @Mock
    private Command command;

    @Test
    void testCommand() {
        RepeatCommand repeatCommand = new RepeatCommand(command);
        repeatCommand.execute();

        Mockito.verify(command, Mockito.times(1)).execute();
    }

    @Test
    void testRepeatCommand() {
        RepeatTwiceCommand repeatTwiceCommand = new RepeatTwiceCommand(command);
        repeatTwiceCommand.execute();

        Mockito.verify(command, Mockito.times(1)).execute();
    }
}