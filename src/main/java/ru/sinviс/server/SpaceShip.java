package ru.sinviс.server;

import static java.lang.Math.*;

public class SpaceShip implements MovingObject, Rotable {
    Point location = new Point(0, 0);
    Angle spaceAngle = new Angle((char) 3, (char) 256);
    Angle velocityAngle = new Angle((char) 1, (char) 256);
    int velocity = 1;

    @Override
    public Point getLocation() {
        return location;
    }

    @Override
    public Point getVelocity() {
        return new Point((int) (velocity * cos((spaceAngle.direction()*1.0/ spaceAngle.directionNumber())/(2 * PI))),
                (int) (velocity * sin((spaceAngle.direction()*1.0/ spaceAngle.directionNumber())/(2 * PI))));
    }

    @Override
    public void setLocation(Point newLocation) {
        this.location = newLocation;
    }


    @Override
    public Angle getSpaceAngle() {
        return spaceAngle;
    }

    @Override
    public Angle getVelocityAngle() {
        return velocityAngle;
    }

    @Override
    public void setNewSpaceAngle(Angle angle) {
        this.spaceAngle = angle;
    }
}
