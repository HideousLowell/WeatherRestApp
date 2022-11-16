package ru.eshakin.WeatherRestApp.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import ru.eshakin.WeatherRestApp.models.dto.SensorDTO;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "Sensor")
public class Sensor {

    @Id
    private String name;

    @OneToMany(mappedBy = "sensor", fetch = FetchType.LAZY)
    List<Measurement> measurements = new ArrayList<>();

    public Sensor(String name) {
        this.name = name;
    }
}
