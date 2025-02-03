package ru.sinviс.server.actions;

import ru.sinviс.server.api.MovingObject;
import ru.sinviс.server.components.Point;

public class Move {
    private final MovingObject obj;

    public Move(MovingObject obj) {
        this.obj = obj;
    }

    public void execute() {
        obj.setLocation(Point.plus(obj.getLocation(), obj.getVelocity()));
    }
}
