package ru.eshakin.weather_app.facade;

import ru.eshakin.weather_app.models.dto.SensorDto;
import ru.eshakin.weather_app.models.entity.Sensor;

import java.util.List;
import java.util.Optional;

public interface SensorFacade {
     void create(SensorDto dto);
     void delete(SensorDto dto);
     List<Sensor> findAll();
     Optional<Sensor> find(String name);
}
