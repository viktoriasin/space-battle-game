package ru.sinviс.server;

public class Rotate {
    private final Rotable obj;

    public Rotate(Rotable obj) {
        this.obj = obj;
    }

    public void execute() {
        obj.setNewSpaceAngle(
                Angle.plus(obj.getSpaceAngle(), obj.getVelocityAngle())
        );
    }
}
