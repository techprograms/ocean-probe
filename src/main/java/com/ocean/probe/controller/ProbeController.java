package com.ocean.probe.controller;

import com.ocean.probe.exception.InvalidDirectionException;
import com.ocean.probe.model.Direction;
import com.ocean.probe.service.ProbeService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/probe")
public class ProbeController {
    private final ProbeService probeService;

    public ProbeController(ProbeService probeService) {
        this.probeService = probeService;
    }

    @PostMapping("/initialize")
    public String initializeProbe(@RequestBody Map<String, Object> request) {
        try {
            int x = (int) request.get("x");
            int y = (int) request.get("y");
            Direction direction = Direction.valueOf((String) request.get("direction"));

            probeService.initializeProbe(x, y, direction);
            return "Probe initialized at (" + x + "," + y + ") facing " + direction;
        } catch (IllegalArgumentException e) {
            throw new InvalidDirectionException("Invalid direction provided: " + request.get("direction"));
        }

    }

    @PostMapping("/move")
    public String moveProbe(@RequestBody Map<String, String> request) {
        return probeService.processCommands(request.get("commands"));
    }

    @GetMapping("/visited")
    public Object getVisited() {
        return probeService.getVisitedCoordinates();
    }
}