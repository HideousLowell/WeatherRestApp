package ru.eshakin.weather_app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.eshakin.weather_app.models.entity.Sensor;

import java.util.Optional;

@Repository
public interface SensorRepo extends CrudRepository<Sensor, String> {
    void deleteByName(String name);
    Optional<Sensor> findByName(String name);
}
