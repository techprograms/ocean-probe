package com.ocean.probe.repository;

import com.ocean.probe.entity.VisitedCoordinate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitedCoordinateRepository extends JpaRepository<VisitedCoordinate, Long> {
}
