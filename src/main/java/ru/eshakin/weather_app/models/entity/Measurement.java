package ru.eshakin.weather_app.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Сущность, отражающая таблицу Measurement
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Measurement")
public class Measurement {

    /**
     * Генерируется в БД
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     *  Температура измерения
     */
    private Double value;

    /**
     *  Показатель дождливости
     */
    private Boolean raining;

    /**
     * Время измерения
     */
    @Column(name = "date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime = new Date();

    /**
     * Сенсор, осуществивший измерение
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "sensor_name", referencedColumnName = "name")
    private Sensor sensor;
}
