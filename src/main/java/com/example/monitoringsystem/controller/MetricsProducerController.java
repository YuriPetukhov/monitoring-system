package com.example.monitoringsystem.controller;

import com.example.monitoringsystem.dto.MetricRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/metrics")
@RequiredArgsConstructor
@Tag(name = "Метрики")
public class MetricsProducerController {

    private final KafkaTemplate<Object, Object> template;

    @PostMapping
    @Operation(summary = "Отправка метрик работы приложения в формате JSON")
    public ResponseEntity<?> sendMetrics(@RequestBody MetricRequestDTO requestDTO) {
        log.info("Received metrics: {}", requestDTO);
        template.send("metrics-topic", requestDTO);
        return ResponseEntity.ok().build();
    }
}

