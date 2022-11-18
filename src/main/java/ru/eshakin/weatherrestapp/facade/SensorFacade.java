package ru.eshakin.weatherrestapp.facade;

import ru.eshakin.weatherrestapp.models.dto.SensorDto;
import ru.eshakin.weatherrestapp.models.entity.Sensor;

import java.util.List;
import java.util.Optional;

public interface SensorFacade {
     void create(SensorDto dto);
     void delete(SensorDto dto);
     List<Sensor> findAll();
     Optional<Sensor> find(String name);
}
