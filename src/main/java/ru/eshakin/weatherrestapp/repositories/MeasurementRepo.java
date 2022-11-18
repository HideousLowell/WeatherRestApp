package ru.eshakin.weatherrestapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.eshakin.weatherrestapp.models.entity.Measurement;

@Repository
public interface MeasurementRepo extends CrudRepository<Measurement, Integer> {
    int countByRainingIsTrue();
}
