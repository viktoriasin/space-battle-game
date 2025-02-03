package ru.sinvic.server.components;

public record Angle(char direction, char directionNumber) {
    public static Angle plus(Angle angle1, Angle angle2) {
        if (angle1.directionNumber() != angle2.directionNumber()) {
            throw new RuntimeException("Direction numbers must be equal to implement plus operation.");
        }
        return new Angle((char) ((angle1.direction + angle2.direction) % angle1.directionNumber()),
                angle1.directionNumber());
    }
}
