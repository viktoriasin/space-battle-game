package ru.sinviс.server.actions;

import ru.sinviс.server.api.RotatableObject;
import ru.sinviс.server.components.Angle;

public class Rotate {
    private final RotatableObject obj;

    public Rotate(RotatableObject obj) {
        this.obj = obj;
    }

    public void execute() {
        obj.setNewSpaceAngle(
                Angle.plus(obj.getAngle(), obj.getAngleVelocity())
        );
    }
}
