package com.example.monitoringsystem.controller;

import com.example.monitoringsystem.dto.MetricResponseDTO;
import com.example.monitoringsystem.mapper.MetricMapper;
import com.example.monitoringsystem.model.Metric;
import com.example.monitoringsystem.repository.MetricsRepository;
import com.example.monitoringsystem.service.MetricsService;
import com.example.monitoringsystem.service.impl.MetricsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class MetricsConsumerControllerTest {

    @Mock
    private MetricsServiceImpl metricsService;
    @Mock
    private MetricsRepository metricsRepository;
    @Mock
    private MetricMapper metricMapper;

    @InjectMocks
    private MetricsConsumerController controller;

    @Test
    public void testGetAllMetrics() throws Exception {
        List<MetricResponseDTO> mockMetrics = new ArrayList<>();
        MetricResponseDTO metricResponseDTO1 = new MetricResponseDTO();
        metricResponseDTO1.setName("metric1");
        metricResponseDTO1.setValue(10.0);
        metricResponseDTO1.setId(1L);

        MetricResponseDTO metricResponseDTO2 = new MetricResponseDTO();
        metricResponseDTO2.setName("metric2");
        metricResponseDTO2.setValue(20.0);
        metricResponseDTO2.setId(2L);

        mockMetrics.add(metricResponseDTO1);
        mockMetrics.add(metricResponseDTO2);
        when(metricsService.getAllMetrics(anyInt(), anyInt())).thenReturn(mockMetrics);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/metrics?page=1&size=10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("metric1"))
                .andExpect(jsonPath("$[0].value").value(10.0))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value("metric2"))
                .andExpect(jsonPath("$[1].value").value(20.0));
    }

    @Test
    public void testGetMetricById() throws Exception {

        MetricResponseDTO mockMetric = new MetricResponseDTO();
        mockMetric.setId(1L);
        mockMetric.setName("metric1");
        mockMetric.setValue(10.0);

        Metric metric = new Metric();
        metric.setId(1L);
        metric.setName("metric1");
        metric.setValue(10.0);
        metric.setTimestamp(LocalDateTime.now());
        metricsRepository.save(metric);
        when(metricsService.getMetricById(anyLong())).thenReturn(mockMetric);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/metrics/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("metric1"))
                .andExpect(jsonPath("$.value").value(10.0));
    }
}
