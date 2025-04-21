package com.ocean.probe.dto;

import lombok.Data;

@Data
public class ProbePositionDTO {
    private int x;
    private int y;
    private String direction;

    public ProbePositionDTO(int x, int y, String direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
}
