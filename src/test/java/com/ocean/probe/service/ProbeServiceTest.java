package com.ocean.probe.service;

import com.ocean.probe.model.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProbeServiceTest {

    ProbeService service = new ProbeService();

    @Test
    void testProbeMovement() {

        service.initializeProbe(2, 2, Direction.NORTH);

        assertEquals("(2,1) facing NORTH", service.processCommands("F"));
        assertEquals("(0,1) facing WEST", service.processCommands("LFF"));
        assertEquals("(0,3) facing NORTH", service.processCommands("RBB"));
    }

    @Test
    void testObstacleAvoidance() {

        service.initializeProbe(2, 2, Direction.NORTH);

        assertEquals("(4,0) facing EAST", service.processCommands("FFRFF"));
    }
}