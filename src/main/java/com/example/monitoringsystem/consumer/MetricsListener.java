package com.example.monitoringsystem.consumer;

import com.example.monitoringsystem.dto.MetricRequestDTO;
import com.example.monitoringsystem.service.MetricsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@KafkaListener(id = "metrics-listener", topics = "metrics-topic")
@RequiredArgsConstructor
public class MetricsListener {

    private final MetricsService metricsService;

    @KafkaHandler
    public void listen(MetricRequestDTO requestDTO) {
        log.info("Received metric: {}", requestDTO);
        metricsService.saveMetric(requestDTO);
    }

    @DltHandler
    public void dltListen(MetricRequestDTO requestDTO) {
        log.warn("Received metric in DLT: {}", requestDTO);
    }
}

