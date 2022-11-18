package ru.eshakin.weatherrestapp.services;

import ru.eshakin.weatherrestapp.models.entity.Sensor;

import java.util.List;
import java.util.Optional;

public interface SensorService {
    List<Sensor> findAll();
    Optional<Sensor> find(String name);
    Optional<Sensor> create(Sensor sensor);
    boolean delete(String name);

}
