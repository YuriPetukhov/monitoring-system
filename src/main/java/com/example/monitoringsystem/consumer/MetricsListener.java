package com.example.monitoringsystem.consumer;

import com.example.monitoringsystem.dto.MetricRequestDTO;
import com.example.monitoringsystem.service.MetricsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@KafkaListener(id = "metrics-listener", topics = "metrics-topic")
public class MetricsListener {

    private final MetricsService metricsService;

    /**
     * Обработка метрик.
     *
     * @param requestDTO Метрики.
     */
    @KafkaHandler
    public void listen(MetricRequestDTO requestDTO) {
        log.info("Received metric: {}", requestDTO);
        metricsService.saveMetric(requestDTO);
    }

    /**
     * Обработка метрик, которые не удалось обработать.
     *
     * @param requestDTO Метрики.
     */
    @DltHandler
    public void dltListen(MetricRequestDTO requestDTO) {
        log.warn("Received metric in DLT: {}", requestDTO);
    }

    /**
     * Обработка сообщений об ошибках.
     *
     * @param errorMessage Сообщение об ошибке.
     */
    @KafkaListener(id = "error-listener", topics = "error-topic")
    public void errorListen(String errorMessage) {
        log.error("Error occurred: {}", errorMessage);
    }
}


