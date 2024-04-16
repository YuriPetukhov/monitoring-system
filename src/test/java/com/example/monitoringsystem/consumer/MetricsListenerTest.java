package com.example.monitoringsystem.consumer;

import com.example.monitoringsystem.service.MetricsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.monitoringsystem.dto.MetricRequestDTO;
import org.slf4j.Logger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MetricsListenerTest {

    @Mock
    private MetricsService metricsService;

    @InjectMocks
    private MetricsListener metricsListener;

    @Test
    public void shouldCallSaveMetricWhenMessageReceived() {
        MetricRequestDTO requestDTO = new MetricRequestDTO();

        metricsListener.listen(requestDTO);

        verify(metricsService).saveMetric(requestDTO);
    }

    @Test
    @DisplayName("Test of getting dlt-object - successful")
    public void shouldLogMessageInDltButNotCallSaveMetric() {
        MetricRequestDTO requestDTO = new MetricRequestDTO();

        metricsListener.dltListen(requestDTO);

        verify(metricsService, never()).saveMetric(any());
    }

    @Test
    @DisplayName("Test of getting an error message - successful")
    public void shouldLogErrorWhenErrorMessageReceived() {
        String errorMessage = "Error occurred";

        metricsListener.errorListen(errorMessage);

        verify(metricsService, never()).saveMetric(any());
    }
}