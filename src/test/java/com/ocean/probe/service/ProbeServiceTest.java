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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProbeServiceTest {

    private ProbeRepository probeRepository;
    private GridRepository gridRepository;
    private VisitedCoordinateRepository visitedCoordinateRepository;
    private ProbeService probeService;

    @BeforeEach
    void setUp() {
        probeRepository = mock(ProbeRepository.class);
        gridRepository = mock(GridRepository.class);
        visitedCoordinateRepository = mock(VisitedCoordinateRepository.class);
        probeService = new ProbeService(probeRepository, gridRepository, visitedCoordinateRepository);
    }

    @Test
    void testInitializeProbe() {
        when(probeRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        String result = probeService.initializeProbe(0, 0, Direction.NORTH);

        assertEquals("Initialized probe at (0, 0) facing NORTH", result);
        verify(gridRepository, times(1)).save(any(Grid.class));
        verify(probeRepository, times(1)).save(any(Probe.class));
    }

    @Test
    void testMoveProbe() {
        Probe probe = new Probe();
        probe.setX(0);
        probe.setY(0);
        probe.setDirection(Direction.NORTH);
        probe.setVisitedCoordinates(new ArrayList<>());

        // Simulate initialization
        when(probeRepository.save(any())).thenReturn(probe);
        probeService.initializeProbe(0, 0, Direction.NORTH);

        // Simulate repository behavior
        when(visitedCoordinateRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        ProbePositionDTO result = probeService.moveProbe("RM");

        assertNotNull(result);
    }

    @Test
    void testMoveWithoutInitializationThrowsException() {
        ProbeService uninitializedService = new ProbeService(probeRepository, gridRepository, visitedCoordinateRepository);
        assertThrows(ProbeNotInitializedException.class, () -> uninitializedService.moveProbe("M"));
    }

    @Test
    void testGetVisitedCoordinates() {
        Probe probe = new Probe();
        probe.setVisitedCoordinates(List.of(
                new VisitedCoordinate(1, 1, probe),
                new VisitedCoordinate(2, 2, probe)
        ));

        probe.setX(2);
        probe.setY(2);
        probe.setDirection(Direction.SOUTH);

        when(probeRepository.save(any())).thenReturn(probe);
        probeService.initializeProbe(0, 0, Direction.NORTH);

        // simulate setting probe state after initialization
        var field = ProbeService.class.getDeclaredFields()[3];
        field.setAccessible(true);
        try { field.set(probeService, probe); } catch (Exception ignored) {}

        List<VisitedCoordinateDTO> visited = probeService.getVisitedCoordinates();

        assertEquals(2, visited.size());
        assertTrue(visited.stream().anyMatch(c -> c.getX() == 1 && c.getY() == 1));
        assertTrue(visited.stream().anyMatch(c -> c.getX() == 2 && c.getY() == 2));
    }
}