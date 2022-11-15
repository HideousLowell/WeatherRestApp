package ru.eshakin.WeatherRestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.eshakin.WeatherRestApp.models.entity.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    void deleteByName(String name);
}
