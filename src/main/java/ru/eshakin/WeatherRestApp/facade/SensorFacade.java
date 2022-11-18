package ru.eshakin.WeatherRestApp.facade;

import ru.eshakin.WeatherRestApp.models.dto.SensorDto;
import ru.eshakin.WeatherRestApp.models.entity.Sensor;

import java.util.List;
import java.util.Optional;

public interface SensorFacade {
     void create(SensorDto dto);
     void delete(SensorDto dto);
     List<Sensor> findAll();
     Optional<Sensor> find(String name);
}
