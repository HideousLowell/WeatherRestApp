package ru.eshakin.WeatherRestApp.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MeasurementResponse {
    private List<MeasurementDTO> measurements;
}
