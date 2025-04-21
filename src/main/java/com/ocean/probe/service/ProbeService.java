package com.ocean.probe.service;

import com.ocean.probe.exception.ProbeNotInitializedException;
import com.ocean.probe.model.Direction;
import com.ocean.probe.model.Grid;
import com.ocean.probe.model.Probe;
import org.springframework.stereotype.Service;

@Service
public class ProbeService {
    private final Grid grid;
    private Probe probe;

    public ProbeService() {
        this.grid = new Grid(10, 10);
        grid.addObstacle(3, 3);
    }

    public void initializeProbe(int x, int y, Direction direction) {
        this.probe = new Probe(x, y, direction, grid);
    }

    public String processCommands(String commands) {
        if (probe == null) {
            throw new ProbeNotInitializedException("Probe not initialized.");
        }
        probe.executeCommands(commands);
        return probe.getCurrentPosition();
    }

    public Object getVisitedCoordinates() {
        return probe.getVisitedCoordinates();
    }
}
