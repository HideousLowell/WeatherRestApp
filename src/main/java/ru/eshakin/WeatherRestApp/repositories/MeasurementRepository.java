package ru.eshakin.WeatherRestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.eshakin.WeatherRestApp.models.entity.Measurement;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {

}
