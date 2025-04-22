package com.ocean.probe.service;

import com.ocean.probe.dto.ProbePositionDTO;
import com.ocean.probe.dto.VisitedCoordinateDTO;
import com.ocean.probe.entity.Grid;
import com.ocean.probe.entity.Probe;
import com.ocean.probe.entity.VisitedCoordinate;
import com.ocean.probe.repository.VisitedCoordinateRepository;
import com.ocean.probe.exception.ProbeNotInitializedException;
import com.ocean.probe.model.Direction;
import com.ocean.probe.repository.GridRepository;
import com.ocean.probe.repository.ProbeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProbeService {
    private final ProbeRepository probeRepository;
    private final GridRepository gridRepository;
    private final VisitedCoordinateRepository visitedCoordinateRepository;

    private Probe probe;

    public ProbeService(ProbeRepository probeRepository, GridRepository gridRepository, VisitedCoordinateRepository visitedCoordinateRepository) {
        this.probeRepository = probeRepository;
        this.gridRepository = gridRepository;
        this.visitedCoordinateRepository = visitedCoordinateRepository;
    }

    public String initializeProbe(int x, int y, Direction direction) {
        Grid grid = new Grid();
        grid.setWidth(10);
        grid.setHeight(10);
        gridRepository.save(grid);

        this.probe = new Probe();
        probe.setX(x);
        probe.setY(y);
        probe.setDirection(direction);
        probe.setGrid(grid);
        probe = probeRepository.save(probe);

        return "Initialized probe at (" + x + ", " + y + ") facing " + direction;
    }

    public ProbePositionDTO moveProbe(String commands) {
        if (probe == null) {
            throw new ProbeNotInitializedException("Probe not initialized.");
        }

        for (char command : commands.toCharArray()) {
            switch (command) {
                case 'L':
                    rotateLeft();
                    break;
                case 'R':
                    rotateRight();
                    break;
                case 'M':
                    moveForward();
                    break;
            }
        }

        probeRepository.save(probe);
        return new ProbePositionDTO(probe.getX(), probe.getY(), probe.getDirection().name());
    }

    public List<VisitedCoordinateDTO> getVisitedCoordinates() {
        return probe.getVisitedCoordinates().stream()
                .map(coordinate -> new VisitedCoordinateDTO(coordinate.getX(), coordinate.getY()))
                .collect(Collectors.toList());
    }

    private void rotateLeft() {
        switch (probe.getDirection()) {
            case NORTH -> probe.setDirection(Direction.WEST);
            case WEST -> probe.setDirection(Direction.SOUTH);
            case SOUTH -> probe.setDirection(Direction.EAST);
            case EAST -> probe.setDirection(Direction.NORTH);
        }
    }

    private void rotateRight() {
        switch (probe.getDirection()) {
            case NORTH -> probe.setDirection(Direction.EAST);
            case EAST -> probe.setDirection(Direction.SOUTH);
            case SOUTH -> probe.setDirection(Direction.WEST);
            case WEST -> probe.setDirection(Direction.NORTH);
        }
    }

    private void moveForward() {
        int x = probe.getX();
        int y = probe.getY();
        switch (probe.getDirection()) {
            case NORTH -> y++;
            case EAST -> x++;
            case SOUTH -> y--;
            case WEST -> x--;
        }

        VisitedCoordinate coordinate = new VisitedCoordinate();
        coordinate.setX(x);
        coordinate.setY(y);
        coordinate.setProbe(probe);

        visitedCoordinateRepository.save(coordinate);
        probe.getVisitedCoordinates().add(coordinate);
        probe.setX(x);
        probe.setY(y);
    }
}
