package ru.eshakin.weather_app.models.dto;

import lombok.*;

import java.util.List;

/**
 * Простая обертка над списком MeasurementDto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementList {
    /**
     * Список измерений
     */
    private List<MeasurementDto> measurements;
}
