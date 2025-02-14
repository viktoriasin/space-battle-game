package ru.sinvic.server.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sinvic.server.api.RotatableObject;
import ru.sinvic.server.components.Angle;

public class RotateCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(RotateCommand.class);

    private RotatableObject obj;

    public RotateCommand(RotatableObject obj) {
        this.obj = obj;
    }

    public RotateCommand() {
    }

    @Override
    public void execute() {
        logger.info("Start executing ...");
        obj.setAngle(
                Angle.plus(obj.getAngle(), obj.getAngleVelocity())
        );
    }
}
