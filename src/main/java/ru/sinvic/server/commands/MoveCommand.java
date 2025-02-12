package ru.sinvic.server.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sinvic.server.api.MovingObject;
import ru.sinvic.server.components.Point;

public class MoveCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(MoveCommand.class);
    private final MovingObject obj;

    public MoveCommand(MovingObject obj) {
        this.obj = obj;
    }

    public MoveCommand() {
        this.obj = null;
    }

    @Override
    public void execute() {
        logger.info("Start executing ...");
        obj.setLocation(Point.plus(obj.getLocation(), obj.getVelocity()));
    }
}
