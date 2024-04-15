package com.example.monitoringsystem.repository;

import com.example.monitoringsystem.model.Metric;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetricsRepository extends JpaRepository<Metric, Long> {
}

