package ru.eshakin.WeatherRestApp.facade;

import org.springframework.stereotype.Service;
import ru.eshakin.WeatherRestApp.models.dto.SensorDto;
import ru.eshakin.WeatherRestApp.models.entity.Sensor;

import java.util.List;
import java.util.Optional;

public interface SensorFacade {
    public void create(SensorDto dto);
    public void delete(SensorDto dto);
    public List<Sensor> findAll();
    public Optional<Sensor> find(String name);
}
