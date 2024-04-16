package com.example.monitoringsystem.demo.service;

import com.example.monitoringsystem.dto.MetricRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class MetricsSender {

    private final RestTemplate restTemplate;

    @Value("${sender.endpoint}")
    private String endpoint;

    /**
     * Отправка метрики.
     *
     * @param metric Метрика.
     */
    public void sendMetrics(MetricRequestDTO metric) {

        log.info("Sending metric: {}", metric);

        try {
            String json = "{\"name\":\"" + metric.getName() + "\",\"value\":" + metric.getValue() + "}";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);

            ResponseEntity<String> response = restTemplate.exchange(endpoint, HttpMethod.POST, requestEntity, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("Metric sent successfully");
            } else {
                log.info("Sending metric failed: {}", endpoint);
            }
        } catch (Exception e) {
            log.error("Error occurred while sending metric to {}", endpoint);
        }
    }
}

