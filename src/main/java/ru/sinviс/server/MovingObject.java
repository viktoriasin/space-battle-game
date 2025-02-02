package ru.sinviс.server;

public interface MovingObject {
    Point getLocation();
    Point getVelocity();
    void setLocation(Point newLocation);
}
