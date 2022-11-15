package ru.eshakin.WeatherRestApp.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.eshakin.WeatherRestApp.models.entity.Measurement;
import ru.eshakin.WeatherRestApp.models.entity.Sensor;

import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SensorDTO {

    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    String name;

    List<Measurement> measurements;

    SensorDTO(Sensor sensor) {
        this.name = sensor.getName();
        this.measurements = sensor.getMeasurements();
    }
}
