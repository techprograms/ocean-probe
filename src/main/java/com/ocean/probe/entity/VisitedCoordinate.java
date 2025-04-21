package com.ocean.probe.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VisitedCoordinate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int x;
    private int y;

    public VisitedCoordinate(int x, int y, Probe probe) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.probe = probe;
    }

    @ManyToOne
    @JoinColumn(name = "probe_id")
    private Probe probe;

}
