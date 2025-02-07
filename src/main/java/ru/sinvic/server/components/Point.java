package ru.sinvic.server.components;

public record Point(int x, int y) {
    public static Point plus(Point firstPoint, Point secondPoint) {
        return new Point(firstPoint.x() + secondPoint.x(), firstPoint.y() + secondPoint.y());
    }
}
