package ru.sinvic.server.actions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sinvic.server.api.RotatableObject;
import ru.sinvic.server.components.Angle;
import ru.sinvic.server.components.Point;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;

@DisplayName("Действие Rotate должно ")
@ExtendWith(MockitoExtension.class)
public class RotateActionTest {
    @Mock
    private RotatableObject rotatableObject;

    private RotateAction rotateAction;

    @BeforeEach
    void setUp() {
        rotateAction = new RotateAction(rotatableObject);
    }

    @DisplayName("верно вращать объект в пространстве")
    @Test
    void testMove() {
        Mockito.when(rotatableObject.getAngle()).thenReturn(new Angle((char) 10, (char) 100));
        Mockito.when(rotatableObject.getAngleVelocity()).thenReturn(new Angle((char) 15, (char) 100));

        rotateAction.execute();

        Mockito.verify(rotatableObject).setAngle(ArgumentMatchers.eq(new Angle((char) 25, (char) 100)));
    }

    @DisplayName("выбросить исключение, при попытке повернуть объект, " +
            "у которого невозможно прочитать угол")
    @Test
    void testMoveWithoutLocation() {
        Mockito.when(rotatableObject.getAngleVelocity()).thenReturn(new Angle((char) 15, (char) 100));

        assertThrows(NullPointerException.class, () -> rotateAction.execute());
    }

    @DisplayName("выбросить исключение, при попытке повернуть объект, " +
            "у которого невозможно прочитать угловую скорость")
    @Test
    void testMoveWithoutVelocity() {
        Mockito.when(rotatableObject.getAngle()).thenReturn(new Angle((char) 10, (char) 100));

        assertThrows(NullPointerException.class, () -> rotateAction.execute());
    }

    @DisplayName("выбросить исключение, при попытке повернуть объект, " +
            "у которого невозможно изменить угол")
    @Test
    void testMoveWithoutSetLocation() {
        doThrow(new RuntimeException()).when(rotatableObject).setAngle(ArgumentMatchers.any());
        Mockito.when(rotatableObject.getAngle()).thenReturn(new Angle((char) 10, (char) 100));
        Mockito.when(rotatableObject.getAngleVelocity()).thenReturn(new Angle((char) 15, (char) 100));

        assertThrows(RuntimeException.class, () -> rotateAction.execute());
    }
}
