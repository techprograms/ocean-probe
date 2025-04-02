package com.ocean.probe.model;

import java.util.ArrayList;
import java.util.List;

public class Probe {
    private int x, y;
    private Direction direction;
    private final Grid grid;
    private final List<String> visited;

    public Probe(int x, int y, Direction direction, Grid grid) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.grid = grid;
        this.visited = new ArrayList<>();
        logPosition();
    }

    public void executeCommands(String commands) {
        for (char command : commands.toCharArray()) {
            switch (command) {
                case 'F' -> move(1);
                case 'B' -> move(-1);
                case 'L' -> direction = direction.turnLeft();
                case 'R' -> direction = direction.turnRight();
                default -> throw new IllegalArgumentException("Invalid command: " + command);
            }
        }
    }

    private void move(int step) {
        int newX = x, newY = y;

        switch (direction) {
            case NORTH -> newY -= step;
            case SOUTH -> newY += step;
            case EAST -> newX += step;
            case WEST -> newX -= step;
        }

        if (!grid.isWithinBounds(newX, newY) || grid.isObstacle(newX, newY)) {
            return; // Ignore move if out of bounds or obstacle
        }

        x = newX;
        y = newY;
        logPosition();
    }

    private void logPosition() {
        visited.add(x + "," + y);
    }

    public List<String> getVisitedCoordinates() {
        return visited;
    }

    public String getCurrentPosition() {
        return "(" + x + "," + y + ") facing " + direction;
    }
}
