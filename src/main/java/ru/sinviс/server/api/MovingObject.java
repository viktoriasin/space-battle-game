package ru.sinviс.server.api;

import ru.sinviс.server.components.Point;

public interface MovingObject {
    Point getLocation();
    Point getVelocity();
    void setLocation(Point newLocation);
}
