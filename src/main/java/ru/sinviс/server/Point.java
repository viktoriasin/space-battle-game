package ru.sinviс.server;

public record Point(int x, int y) {
    static Point plus(Point firstPoint, Point secondPoint) {
        return new Point(firstPoint.x() + secondPoint.x(), firstPoint.y() + secondPoint.y());
    }
}
