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
import ru.sinvic.server.exceptions.handlers.LogExceptionHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LogExceptionHandlerTest {

    private final String TEST_ERROR_MESSAGE = "testError";

    @Mock
    private Command command;
    @Mock
    private Exception exception;
    @Mock
    private Appender mockedAppender;
    @Captor
    private ArgumentCaptor<LoggingEvent> loggingEventCaptor;

    @BeforeEach
    public void setup() {
        Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        root.addAppender(mockedAppender);
        when(exception.getMessage()).thenReturn(TEST_ERROR_MESSAGE);
    }

    @AfterEach
    public void teardown() {
        final Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        logger.detachAppender(mockedAppender);
    }

    @Test
    public void testCommand() {
        LogExceptionHandler logExceptionHandler = new LogExceptionHandler(command, exception);

        logExceptionHandler.execute();

        verify(mockedAppender, times(1)).doAppend(loggingEventCaptor.capture());
        LoggingEvent loggingEvent = loggingEventCaptor.getAllValues().getFirst();

        assertTrue(loggingEvent.getFormattedMessage().endsWith(TEST_ERROR_MESSAGE));
        assertEquals(Level.ERROR, loggingEvent.getLevel());
    }
}