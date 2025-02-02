package ru.sinviс.server;

public record Angle(char direction, char directionNumber) {
    static Angle plus(Angle angle1, Angle angle2) {
        if (angle1.directionNumber() != angle2.directionNumber()) {
            throw new RuntimeException("Direction numbers must be equal to implement plus operation.");
        }
        return new Angle((char) ((angle1.direction + angle2.direction) % angle1.directionNumber()),
                angle1.directionNumber());
    }
}
