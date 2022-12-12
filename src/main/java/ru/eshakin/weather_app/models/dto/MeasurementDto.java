package ru.eshakin.weather_app.models.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * DTO для класса Measurement
 * @see ru.eshakin.weather_app.models.entity.Measurement
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementDto {

    /**
     *  Температура измерения
     */
    @Min(value = -100, message = "The temperature should be over -100 degrees")
    @Max(value = 100, message = "The temperature should be below 100 degrees")
    @NotNull
    private Double value;

    /**
     *  Показатель дожливости
     */
    @NotNull
    private Boolean raining;

    /**
     *  Сенсор, который осуществил измерение
     */
    @NotNull
    private SensorDto sensor;
}
