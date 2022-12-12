package ru.eshakin.weather_app.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


/**
 * DTO для класса Sensor
 * @see ru.eshakin.weather_app.models.entity.Sensor
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SensorDto {

    /**
     * Имя сенсора
     */
    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 30, message = "Name should be between 3 and 30 characters")
    private String name;
}
