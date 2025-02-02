package ru.sinviс.server;

public interface Rotable {
    Angle getSpaceAngle();
    Angle getVelocityAngle();
    void setNewSpaceAngle(Angle angle);
}
