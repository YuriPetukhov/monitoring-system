package com.example.monitoringsystem.service.impl;

import com.example.monitoringsystem.dto.MetricRequestDTO;
import com.example.monitoringsystem.dto.MetricResponseDTO;
import com.example.monitoringsystem.mapper.MetricMapper;
import com.example.monitoringsystem.model.Metric;
import com.example.monitoringsystem.repository.MetricsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MetricsServiceImplTest {

    @Mock
    private MetricsRepository metricsRepository;

    @Mock
    private MetricMapper metricMapper;

    @InjectMocks
    private MetricsServiceImpl metricsService;

    @Test
    @DisplayName("Test getting of metrics list - successful")
    public void shouldReturnListOfMetrics() {
        Pageable pageable = PageRequest.of(0, 10);
        List<Metric> metrics = Collections.singletonList(new Metric(1L, "test", 10.0, LocalDateTime.now()));
        PageImpl<Metric> page = new PageImpl<>(metrics, pageable, metrics.size());

        List<MetricResponseDTO> expectedMetrics = metrics.stream()
                .map(metricMapper::toMetricResponseDTO)
                .collect(Collectors.toList());

        when(metricsRepository.findAll(pageable)).thenReturn(page);

        List<MetricResponseDTO> actualMetrics = metricsService.getAllMetrics(1, 10);

        assertEquals(expectedMetrics, actualMetrics);
    }


    @Test
    @DisplayName("Test getting of the metric by id - successful")
    public void shouldReturnMetricById() {
        Long id = 1L;
        Metric metric = new Metric(id, "test", 10.0, LocalDateTime.now());
        MetricResponseDTO expectedMetric = metricMapper.toMetricResponseDTO(metric);

        when(metricsRepository.findById(id)).thenReturn(Optional.of(metric));

        MetricResponseDTO actualMetric = metricsService.getMetricById(id);

        assertEquals(expectedMetric, actualMetric);
    }

    @Test
    @DisplayName("Test saving of a metric - successful")
    public void shouldSaveMetric() {
        MetricRequestDTO metricRequestDTO = new MetricRequestDTO();
        metricRequestDTO.setName("test");
        metricRequestDTO.setValue(10.0);
        Metric metric = metricMapper.toEntityMetric(metricRequestDTO);

        metricsService.saveMetric(metricRequestDTO);

        verify(metricsRepository).save(metric);
    }
}