package com.ocean.probe.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Grid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int width;
    private int height;

    @OneToMany(mappedBy = "grid", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Obstacle> obstacles = new HashSet<>();
}
