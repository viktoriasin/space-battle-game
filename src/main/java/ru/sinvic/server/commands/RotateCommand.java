package ru.sinvic.server.commands;

import ru.sinvic.server.api.RotatableObject;
import ru.sinvic.server.components.Angle;

public class RotateCommand implements Command {
    private final RotatableObject obj;

    public RotateCommand(RotatableObject obj) {
        this.obj = obj;
    }

    @Override
    public void execute() {
        obj.setAngle(
                Angle.plus(obj.getAngle(), obj.getAngleVelocity())
        );
    }
}
