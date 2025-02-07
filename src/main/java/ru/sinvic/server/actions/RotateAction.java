package ru.sinvic.server.actions;

import ru.sinvic.server.api.RotatableObject;
import ru.sinvic.server.components.Angle;

public class RotateAction implements Action {
    private final RotatableObject obj;

    public RotateAction(RotatableObject obj) {
        this.obj = obj;
    }

    @Override
    public void execute() {
        obj.setAngle(
                Angle.plus(obj.getAngle(), obj.getAngleVelocity())
        );
    }
}
