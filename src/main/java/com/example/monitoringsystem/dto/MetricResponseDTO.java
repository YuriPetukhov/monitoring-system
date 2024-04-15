package com.example.monitoringsystem.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class MetricResponseDTO {
    private Long id;
    private String name;
    private Double value;
    private LocalDateTime timestamp;
}
