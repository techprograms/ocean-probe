package com.ocean.probe.repository;


import com.ocean.probe.entity.Probe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProbeRepository extends JpaRepository<Probe, Long> {
}
