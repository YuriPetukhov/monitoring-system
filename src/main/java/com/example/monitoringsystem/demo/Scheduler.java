package com.example.monitoringsystem.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Методы класса устанавливают время, когда должны запускаться
 * определенные методы
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class Scheduler {
    private final MetricsGenerator metricsGenerator;

    @Scheduled(fixedRate = 1000)
    public void generateAndSendMetrics() {
        metricsGenerator.generateAndSendMetrics();
    }

}
