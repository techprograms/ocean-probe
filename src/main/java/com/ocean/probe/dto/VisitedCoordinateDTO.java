package com.ocean.probe.dto;

import lombok.Data;

@Data
public class VisitedCoordinateDTO {
    private int x;
    private int y;

    public VisitedCoordinateDTO(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
