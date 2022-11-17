package ru.eshakin.WeatherRestApp.services;

import org.springframework.transaction.annotation.Transactional;
import ru.eshakin.WeatherRestApp.models.entity.Sensor;

import java.util.List;
import java.util.Optional;

public interface SensorService {
    public List<Sensor> findAll();
    public Optional<Sensor> find(String name);
    @Transactional
    public Optional<Sensor> create(Sensor sensor);
    @Transactional
    public boolean delete(String name);

}
