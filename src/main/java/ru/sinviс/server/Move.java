package ru.sinviс.server;

public class Move {
    private final MovingObject obj;

    public Move(MovingObject obj) {
        this.obj = obj;
    }

    public void execute() {
        obj.setLocation(Point.plus(obj.getLocation(), obj.getVelocity()));
    }
}
