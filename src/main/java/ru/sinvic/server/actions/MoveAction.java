package ru.sinvic.server.actions;

import ru.sinvic.server.api.MovingObject;
import ru.sinvic.server.components.Point;

public class MoveAction implements Action {
    private final MovingObject obj;

    public MoveAction(MovingObject obj) {
        this.obj = obj;
    }

    @Override
    public void execute() {
        obj.setLocation(Point.plus(obj.getLocation(), obj.getVelocity()));
    }
}
