package ru.sinvic.server.commands;

import ru.sinvic.server.api.MovingObject;
import ru.sinvic.server.components.Point;

public class MoveCommand implements Command {
    private final MovingObject obj;

    public MoveCommand(MovingObject obj) {
        this.obj = obj;
    }

    public MoveCommand() {
        this.obj = null;
    }

    @Override
    public void execute() {
        obj.setLocation(Point.plus(obj.getLocation(), obj.getVelocity()));
    }
}
