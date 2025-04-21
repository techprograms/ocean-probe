package com.ocean.probe.controller;

import com.ocean.probe.dto.ProbePositionDTO;
import com.ocean.probe.dto.VisitedCoordinateDTO;
import com.ocean.probe.model.Direction;
import com.ocean.probe.service.ProbeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/probe")
public class ProbeController {
    private final ProbeService probeService;

    public ProbeController(ProbeService probeService) {
        this.probeService = probeService;
    }

    @PostMapping("/initialize")
    public String initialize(@RequestBody Map<String, Object> request) {
        int x = (int) request.get("x");
        int y = (int) request.get("y");
        Direction direction = Direction.valueOf(((String) request.get("direction")).toUpperCase());
        return probeService.initializeProbe(x, y, direction);
    }

    @PostMapping("/move")
    public ProbePositionDTO move(@RequestBody Map<String, String> request) {
        return probeService.moveProbe(request.get("commands"));
    }

    @GetMapping("/visited")
    public List<VisitedCoordinateDTO> visited() {
        return probeService.getVisitedCoordinates();
    }
}
