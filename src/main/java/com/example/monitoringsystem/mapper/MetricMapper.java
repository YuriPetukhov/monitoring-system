package com.example.monitoringsystem.mapper;

import com.example.monitoringsystem.dto.MetricRequestDTO;
import com.example.monitoringsystem.dto.MetricResponseDTO;
import com.example.monitoringsystem.model.Metric;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MetricMapper {

    MetricResponseDTO toMetricResponseDTO(Metric metric);
    @Mapping(source = "name", target = "name")
    @Mapping(source = "value", target = "value")
    Metric toEntityMetric(MetricRequestDTO metricRequestDTO);
}
