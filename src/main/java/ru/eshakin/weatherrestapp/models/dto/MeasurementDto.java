package ru.eshakin.weatherrestapp.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementDto {

    @Min(value = -100, message = "The temperature should be over -100 degrees")
    @Max(value = 100, message = "The temperature should be below 100 degrees")
    @NotNull
    private Double value;

    @NotNull
    private Boolean raining;

    @NotNull
    private SensorDto sensor;
}
