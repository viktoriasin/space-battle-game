package ru.sinvic.server;

import ru.sinvic.server.api.RotatableObject;
import ru.sinvic.server.components.Angle;
import ru.sinvic.server.dto.UObject;

public class RotatableObjectAdapter implements RotatableObject {
    private final UObject obj;

    public RotatableObjectAdapter(UObject obj) {
        this.obj = obj;
    }

    @Override
    public Angle getAngle() {
        return (Angle) obj.getValue("angle");
    }

    @Override
    public Angle getAngleVelocity() {
        return (Angle) obj.getValue("angleVelocity");
    }

    @Override
    public void setAngle(Angle angle) {
        obj.setValue("angle", angle);
    }
}
