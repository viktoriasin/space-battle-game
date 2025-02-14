package ru.sinvic.server.commands;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.Appender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LogCommandTest {

    @Mock
    private Appender mockedAppender;
    @Captor
    private ArgumentCaptor<LoggingEvent> loggingEventCaptor;

    @BeforeEach
    public void setup() {
        Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        root.addAppender(mockedAppender);
    }

    @AfterEach
    public void teardown() {
        final Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        logger.detachAppender(mockedAppender);
    }

    @Test
    public void testLogCommandInfo() {
        String infoMessage = "info message";
        LogCommand logCommand = new LogCommand(infoMessage);

        logCommand.execute();

        verify(mockedAppender, times(1)).doAppend(loggingEventCaptor.capture());
        LoggingEvent loggingEvent = loggingEventCaptor.getAllValues().getFirst();

        assertEquals(infoMessage, loggingEvent.getFormattedMessage());
        assertEquals(Level.INFO, loggingEvent.getLevel());
    }

    @Test
    public void testLogCommandError() {
        String errorMessage = "error message";
        LogCommand logCommand = new LogCommand(errorMessage, true);

        logCommand.execute();

        verify(mockedAppender, times(1)).doAppend(loggingEventCaptor.capture());
        LoggingEvent loggingEvent = loggingEventCaptor.getAllValues().getFirst();

        assertEquals(errorMessage, loggingEvent.getFormattedMessage());
        assertEquals(Level.ERROR, loggingEvent.getLevel());
    }
}