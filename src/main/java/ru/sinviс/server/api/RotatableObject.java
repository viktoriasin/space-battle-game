package ru.sinviс.server.api;

import ru.sinviс.server.components.Angle;

public interface RotatableObject {
    Angle getAngle();
    Angle getAngleVelocity();
    void setNewSpaceAngle(Angle angle);
}
