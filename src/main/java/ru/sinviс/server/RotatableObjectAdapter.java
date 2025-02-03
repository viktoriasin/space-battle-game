package ru.sinviс.server;

import ru.sinviс.server.api.RotatableObject;
import ru.sinviс.server.components.Angle;
import ru.sinviс.server.dto.UObject;

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
    public void setNewSpaceAngle(Angle angle) {
        obj.setValue("angle", angle);
    }
}
