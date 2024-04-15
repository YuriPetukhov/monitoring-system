package com.example.monitoringsystem.service;

import com.example.monitoringsystem.dto.MetricRequestDTO;
import com.example.monitoringsystem.dto.MetricResponseDTO;

import java.util.List;

public interface MetricsService {

    List<MetricResponseDTO> getAllMetrics(Integer pageNumber, Integer pageSize);

    MetricResponseDTO getMetricById(Long id);

    void saveMetric(MetricRequestDTO requestDTO);
}
