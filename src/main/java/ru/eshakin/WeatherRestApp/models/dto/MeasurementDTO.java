package ru.eshakin.WeatherRestApp.models.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.eshakin.WeatherRestApp.models.entity.Sensor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementDTO {

    @Min(value = -100, message = "The temperature should be above -100 degrees")
    @Max(value = 100, message = "The temperature should be 100 degrees")
    @NotNull
    private double value;

    private boolean raining;

    private Date dateTime;

    private Sensor sensor;
}
