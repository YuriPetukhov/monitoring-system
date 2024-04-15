package com.example.monitoringsystem.service.impl;

import com.example.monitoringsystem.dto.MetricRequestDTO;
import com.example.monitoringsystem.dto.MetricResponseDTO;
import com.example.monitoringsystem.exception.MetricNotFoundException;
import com.example.monitoringsystem.mapper.MetricMapper;
import com.example.monitoringsystem.model.Metric;
import com.example.monitoringsystem.repository.MetricsRepository;
import com.example.monitoringsystem.service.MetricsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MetricsServiceImpl implements MetricsService {

    private final MetricsRepository metricsRepository;
    private final MetricMapper metricMapper;

    /**
     * Получение всех метрик.
     *
     * @param pageNumber Номер страницы.
     * @param pageSize Размер страницы.
     * @return Список метрик.
     */
    @Override
    public List<MetricResponseDTO> getAllMetrics(Integer pageNumber, Integer pageSize) {
        log.info("list of metrics");
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        List<Metric> metrics = metricsRepository.findAll(pageRequest).getContent();
        return metrics.stream()
                .map(metricMapper::toMetricResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Получение метрики по идентификатору.
     *
     * @param id Идентификатор метрики.
     * @return Метрика.
     * @throws MetricNotFoundException Ошибка, если метрика не найдена.
     */
    @Override
    public MetricResponseDTO getMetricById(Long id) {
        log.info("metric by Id");
        Optional<Metric> metricOpt = metricsRepository.findById(id);
        if (metricOpt.isPresent()) {
            return metricMapper.toMetricResponseDTO(metricOpt.get());
        } else {
            throw new MetricNotFoundException("Metric not found with id: " + id);
        }
    }

    /**
     * Сохранение метрики.
     *
     * @param requestDTO Метрика.
     */
    @Override
    public void saveMetric(MetricRequestDTO requestDTO) {
        log.info("new metric {}", requestDTO.getName());
        metricsRepository.save(metricMapper.toEntityMetric(requestDTO));
    }

}

