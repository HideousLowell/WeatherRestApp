package ru.eshakin.weatherrestapp.models.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementList {
    private List<MeasurementDto> measurements;
}
