package com.example.monitoringsystem.mapper;

import com.example.monitoringsystem.dto.MetricRequestDTO;
import com.example.monitoringsystem.dto.MetricResponseDTO;
import com.example.monitoringsystem.model.Metric;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MetricMapper {

    MetricResponseDTO toMetricResponseDTO(Metric metric);

    Metric toEntityMetric(MetricRequestDTO responseDTO);
}
