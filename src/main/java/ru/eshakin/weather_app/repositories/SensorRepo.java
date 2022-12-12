package ru.eshakin.weather_app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.eshakin.weather_app.models.entity.Sensor;

import java.util.Optional;


/**
 * Репозиторий для взаимодействия с таблицей Sensor
 */
@Repository
public interface SensorRepo extends CrudRepository<Sensor, String> {

    /**
     * @param name имя удаляемого сенсора
     */
    void deleteByName(String name);

    /**
     * @param name имя искомого сенсора
     * @return найденный сенсор или пустой optional
     */
    Optional<Sensor> findByName(String name);
}
