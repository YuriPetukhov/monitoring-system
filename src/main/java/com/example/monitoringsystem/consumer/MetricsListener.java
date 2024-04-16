package com.example.monitoringsystem.consumer;

import com.example.monitoringsystem.dto.MetricRequestDTO;
import com.example.monitoringsystem.service.MetricsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional
public class MetricsListener {

    private final MetricsService metricsService;

    @KafkaListener(id = "metrics-listener", topics = "metrics-topic")
    public void listen(MetricRequestDTO requestDTO) {
        log.info("Received metric: {}", requestDTO);
        metricsService.saveMetric(requestDTO);
    }

    @KafkaListener(id = "dlt-listener", topics = "metrics-topic")
    public void dltListen(MetricRequestDTO requestDTO) {
        log.warn("Received metric in DLT: {}", requestDTO);
    }

    @KafkaListener(id = "error-listener", topics = "error-topic")
    public void errorListen(String errorMessage) {
        log.error("Error occurred: {}", errorMessage);
    }
}


