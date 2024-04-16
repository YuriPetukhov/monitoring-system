package com.example.monitoringsystem.demo.service;

import com.example.monitoringsystem.demo.exception.OutOfMaterialException;
import com.example.monitoringsystem.dto.MetricRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class MetricsGenerator {

    private final MetricsSender metricsSender;
    private final ScheduledExecutorService executorService;

    /**
     * Генерация и отправка метрик.
     */
    public void generateAndSendMetrics() {
        long currentTime = System.currentTimeMillis();
        executorService.schedule(() -> sendMetric("MaterialAcquisition", currentTime), 0, TimeUnit.SECONDS);
        executorService.schedule(() -> sendMetric("MaterialProcessing", currentTime), 1, TimeUnit.SECONDS);
        executorService.schedule(() -> sendMetric("DetailManufacturing", currentTime), 2, TimeUnit.SECONDS);
        executorService.schedule(() -> sendMetric("SendingToWarehouse", currentTime), 3, TimeUnit.SECONDS);
    }

    /**
     * Генерация ошибки.
     */
    public void generateAndSendError() {
        log.error("Simulated error occurred!");
        throw new OutOfMaterialException();
    }

    /**
     * Отправка метрики.
     *
     * @param operation Операция.
     * @param time Время.
     */
    private void sendMetric(String operation, double time) {
        MetricRequestDTO metric = new MetricRequestDTO();
        metric.setName(operation);
        metric.setValue(time);
        metricsSender.sendMetrics(metric);
    }

}
