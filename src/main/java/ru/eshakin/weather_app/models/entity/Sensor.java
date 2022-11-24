package ru.eshakin.weather_app.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    private List<Measurement> measurements;

}
