package ru.eshakin.WeatherRestApp.services;

import ru.eshakin.WeatherRestApp.models.entity.Sensor;

import java.util.List;
import java.util.Optional;

public interface SensorService {
    List<Sensor> findAll();
    Optional<Sensor> find(String name);
    Optional<Sensor> create(Sensor sensor);
    boolean delete(String name);

}
