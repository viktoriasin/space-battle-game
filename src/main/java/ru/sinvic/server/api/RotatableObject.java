package ru.sinvic.server.api;

import ru.sinvic.server.components.Angle;

public interface RotatableObject {
    Angle getAngle();

    Angle getAngleVelocity();

    void setAngle(Angle angle);
}
