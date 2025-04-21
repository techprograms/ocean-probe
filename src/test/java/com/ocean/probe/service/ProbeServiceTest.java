package com.ocean.probe.service;

import com.ocean.probe.exception.ProbeNotInitializedException;
import com.ocean.probe.model.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProbeServiceTest {
    private ProbeService probeService;

    @BeforeEach
    void setUp() {
        probeService = new ProbeService();
    }

    @Test
    void testInitializeProbe_andProcessCommands() {
        //Arrange
        probeService.initializeProbe(0, 1, Direction.WEST);
        //Act
        String result = probeService.processCommands("LFF");
        //Assert
        assertNotNull(result);
    }

    @Test
    void testProcessCommands_withoutInitialization_throwsException() {
        ProbeNotInitializedException exception = assertThrows(ProbeNotInitializedException.class, () -> {
            probeService.processCommands("MM");
        });
        assertEquals("Probe not initialized.", exception.getMessage());
    }

    @Test
    void testVisitedCoordinates_afterMovement() {
        //Arrange
        probeService.initializeProbe(2, 1, Direction.NORTH);
        probeService.processCommands("F");
        //Act
        Object visited = probeService.getVisitedCoordinates();
        //Assert
        assertNotNull(visited);

    }

    @Test
    void testObstacleAvoidance() {
        //Arrange
        probeService.initializeProbe(2, 2, Direction.NORTH);
        //Assert
        assertEquals("(4,0) facing EAST", probeService.processCommands("FFRFF"));
    }
}