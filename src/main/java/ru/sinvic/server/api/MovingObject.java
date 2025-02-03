package ru.sinvic.server.api;

import ru.sinvic.server.components.Point;

public interface MovingObject {
    Point getLocation();

    Point getVelocity();

    void setLocation(Point newLocation);
}
