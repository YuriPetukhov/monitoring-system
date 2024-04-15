package com.example.monitoringsystem.controller;

import com.example.monitoringsystem.dto.MetricResponseDTO;
import com.example.monitoringsystem.service.MetricsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping(value = "/metrics")
@RequiredArgsConstructor
@Tag(name = "Метрики")
public class MetricsConsumerController {

    private final MetricsService metricsService;

    @GetMapping
    @Operation(summary = "Получение списка всех метрик")
    public ResponseEntity<List<MetricResponseDTO>> getAllMetrics(
            @RequestParam(value = "page") Integer pageNumber,
            @RequestParam(value = "size") Integer pageSize){
        log.info("Received request to retrieve all metrics");
        List<MetricResponseDTO> metrics = metricsService.getAllMetrics(pageNumber, pageSize);
        return ResponseEntity.ok().body(metrics);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение конкретной метрики по ее идентификатору")
    public ResponseEntity<MetricResponseDTO> getMetricById(@PathVariable Long id) {
        log.info("Received request to retrieve metric with id: {}", id);
        MetricResponseDTO responseDTO = metricsService.getMetricById(id);
        return ResponseEntity.ok().body(responseDTO);
    }
}
