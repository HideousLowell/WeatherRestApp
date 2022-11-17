package ru.eshakin.WeatherRestApp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.eshakin.WeatherRestApp.models.entity.Measurement;

@Repository
public interface MeasurementRepository extends CrudRepository<Measurement, Integer> {
    int countByRainingIsTrue();
}
