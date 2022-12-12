package ru.eshakin.weather_app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.eshakin.weather_app.models.entity.Measurement;


/**
 * Репозиторий для взаимодействия с таблицей Measurement
 */
@Repository
public interface MeasurementRepo extends CrudRepository<Measurement, Integer> {

    /**
     * @return количество дождливых дней среди всех измерений
     */
    int countByRainingIsTrue();
}
