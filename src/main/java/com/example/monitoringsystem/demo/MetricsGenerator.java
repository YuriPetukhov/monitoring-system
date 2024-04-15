package com.example.monitoringsystem.demo;

import com.example.monitoringsystem.dto.MetricRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
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
    @Scheduled(fixedRate = 1000)
    public void generateAndSendMetrics() {
        long currentTime = System.currentTimeMillis();
        executorService.schedule(() -> sendMetric("MaterialAcquisition", currentTime), 0, TimeUnit.SECONDS);
        executorService.schedule(() -> sendMetric("MaterialProcessing", currentTime), 1, TimeUnit.SECONDS);
        executorService.schedule(() -> sendMetric("DetailManufacturing", currentTime), 2, TimeUnit.SECONDS);
        executorService.schedule(() -> sendMetric("SendingToWarehouse", currentTime), 3, TimeUnit.SECONDS);
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
