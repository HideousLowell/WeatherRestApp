package ru.eshakin.WeatherRestApp.models.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class MeasurementResponse {
    private final List<MeasurementDto> measurements;
}
