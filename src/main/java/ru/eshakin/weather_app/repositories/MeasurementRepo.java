package ru.eshakin.weather_app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.eshakin.weather_app.models.entity.Measurement;

@Repository
public interface MeasurementRepo extends CrudRepository<Measurement, Integer> {
    int countByRainingIsTrue();
}
