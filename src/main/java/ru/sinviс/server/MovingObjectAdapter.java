package ru.sinviс.server;

import ru.sinviс.server.api.MovingObject;
import ru.sinviс.server.components.Angle;
import ru.sinviс.server.components.Point;
import ru.sinviс.server.dto.UObject;

import static java.lang.Math.*;
import static java.lang.Math.PI;

public class MovingObjectAdapter implements MovingObject {

    private final UObject obj;

    public MovingObjectAdapter(UObject obj) {
        this.obj = obj;
    }

    @Override
    public Point getLocation() {
        return (Point) obj.getValue("location");
    }

    @Override
    public Point getVelocity() {
        int speed = (int) obj.getValue("speed");
        Angle angle = (Angle) obj.getValue("angle");

        return new Point((int) (speed * cos((angle.direction()*1.0/ angle.directionNumber())/(2 * PI))),
                (int) (speed * sin((angle.direction()*1.0/ angle.directionNumber())/(2 * PI))));
    }

    @Override
    public void setLocation(Point newLocation) {
        obj.setValue("location", newLocation);
    }
}
