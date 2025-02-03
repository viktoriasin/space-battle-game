package ru.sinvic.server;

import ru.sinvic.server.api.MovingObject;
import ru.sinvic.server.components.Angle;
import ru.sinvic.server.components.Point;
import ru.sinvic.server.dto.UObject;

import static java.lang.Math.*;

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
        return new Point((int) Math.round(speed * cos(((float) angle.direction() / angle.directionNumber()) / (2 * PI))),
                (int) Math.round(speed * sin(((float) angle.direction() / angle.directionNumber()) / (2 * PI))));
    }

    @Override
    public void setLocation(Point newLocation) {
        obj.setValue("location", newLocation);
    }
}
