package ru.sinvic.server.actions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sinvic.server.api.MovingObject;
import ru.sinvic.server.components.Point;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;

@DisplayName("Действие Move должно ")
@ExtendWith(MockitoExtension.class)
class MoveActionTest {

    @Mock
    private MovingObject movingObject;

    private MoveAction moveAction;

    @BeforeEach
    void setUp() {
        moveAction = new MoveAction(movingObject);
    }

    @DisplayName("верно перемещать объект в пространстве")
    @Test
    void testMove() {
        Mockito.when(movingObject.getLocation()).thenReturn(new Point(12, 5));
        Mockito.when(movingObject.getVelocity()).thenReturn(new Point(-7, 3));

        moveAction.execute();

        Mockito.verify(movingObject).setLocation(ArgumentMatchers.eq(new Point(5, 8)));
    }

    @DisplayName("выбросить исключение, при попытке сдвинуть объект, " +
            "у которого невозможно прочитать положение в пространстве")
    @Test
    void testMoveWithoutLocation() {
        Mockito.when(movingObject.getVelocity()).thenReturn(new Point(-7, 3));

        assertThrows(NullPointerException.class, () -> moveAction.execute());
    }

    @DisplayName("выбросить исключение, при попытке сдвинуть объект, " +
            "у которого невозможно прочитать мгновенную скорость")
    @Test
    void testMoveWithoutVelocity() {
        Mockito.when(movingObject.getLocation()).thenReturn(new Point(12, 5));

        assertThrows(NullPointerException.class, () -> moveAction.execute());
    }

    @DisplayName("выбросить исключение, при попытке сдвинуть объект, " +
            "у которого невозможно изменить положение в пространстве")
    @Test
    void testMoveWithoutSetLocation() {
        doThrow(new RuntimeException()).when(movingObject).setLocation(ArgumentMatchers.any());
        Mockito.when(movingObject.getLocation()).thenReturn(new Point(12, 5));
        Mockito.when(movingObject.getVelocity()).thenReturn(new Point(-7, 3));

        assertThrows(RuntimeException.class, () -> moveAction.execute());
    }
}