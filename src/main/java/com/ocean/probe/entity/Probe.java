package com.ocean.probe.entity;

import com.ocean.probe.model.Direction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Probe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int x;
    private int y;

    @Enumerated(EnumType.STRING)
    private Direction direction;

    @ManyToOne
    @JoinColumn(name = "grid_id")
    private Grid grid;

    @OneToMany(mappedBy = "probe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VisitedCoordinate> visitedCoordinates = new ArrayList<>();
}
