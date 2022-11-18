package ru.eshakin.WeatherRestApp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.eshakin.WeatherRestApp.models.entity.Measurement;
import ru.eshakin.WeatherRestApp.models.entity.Sensor;

import java.util.List;

@Repository
public interface MeasurementRepo extends CrudRepository<Measurement, Integer> {
    int countByRainingIsTrue();
}
