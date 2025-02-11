package ru.sinvic.server;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sinvic.server.commands.Command;
import ru.sinvic.server.exceptions.ExceptionHandler;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("CommandLooper должен ")
@ExtendWith(MockitoExtension.class)
class CommandLooperTest {

    @Mock
    private Command command;
    @Mock
    private Command fallbackCommand;
    @Mock
    private ExceptionHandler exceptionHandler;

    private CommandLooper commandLooper;


    @BeforeEach
    public void setUp() {
        commandLooper = new CommandLooper(exceptionHandler);
    }

    @DisplayName("Вызывать метод execute у команды ровно один раз " +
            "и не вызывать метод handle у обработчика исключений, в случае если команда завершилась успешно.")
    @Test
    public void testLoop() {
        commandLooper.schedule(command);
        commandLooper.loop();
        Mockito.verify(command, times(1)).execute();
        Mockito.verify(exceptionHandler, never()).handle(any(), any());
    }

    @DisplayName("Не вызывать никаких методов, если цикл обработки команд не был запущен.")
    @Test
    public void testNotLoop() {
        commandLooper.schedule(command);
        Mockito.verify(command, never()).execute();
        Mockito.verify(exceptionHandler, never()).handle(any(), any());
    }

    @DisplayName("Вызывать метод execute у команды ровно один раз " +
            "и вызвать метод handle у обработчика исключений " +
            "и execute у fallback команды, в случае если команда завершилась с ошибкой.")
    @Test
    public void testExceptionHandler() {
        doThrow(new RuntimeException()).when(command).execute();
        when(exceptionHandler.handle(any(), any())).thenReturn(fallbackCommand);
        commandLooper.schedule(command);
        commandLooper.loop();
        Mockito.verify(command, times(1)).execute();
        Mockito.verify(exceptionHandler, times(1)).handle(any(), any());
        Mockito.verify(fallbackCommand, times(1)).execute();
    }
}