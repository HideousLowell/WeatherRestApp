package ru.eshakin.WeatherRestApp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.eshakin.WeatherRestApp.models.entity.Sensor;

import java.util.Optional;

@Repository
public interface SensorRepo extends CrudRepository<Sensor, String> {
    void deleteByName(String name);
    Optional<Sensor> findByName(String name);
}
