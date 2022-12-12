package ru.eshakin.weather_app.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Сущность, отражающая таблицу Sensor
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Sensor")
public class Sensor {

    /**
     * Имя сенсора - первичный ключ
     */
    @Id
    private String name;

    /**
     * Список измерений сенсора (FetchType.LAZY)
     */
    @OneToMany(mappedBy = "sensor", fetch = FetchType.LAZY)
    private List<Measurement> measurements;
}
