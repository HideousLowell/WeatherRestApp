package ru.eshakin.weather_app.services;

import ru.eshakin.weather_app.models.entity.Sensor;

import java.util.List;
import java.util.Optional;

/**
 *  * Сервис для взаимодействия с репозиторием Sensor
 */
public interface SensorService {
    /**
     * @return список всех сенсоров
     */
    List<Sensor> findAll();
    /**
     * @param name - имя искомого сенсора
     * @return optional сенсор из БД
     */
    Optional<Sensor> find(String name);
    /**
     * @param sensor - Добавляемый в БД сенсор
     * @return созданный сенсор, или пустой Optional в случае неудачи
     */
    Optional<Sensor> create(Sensor sensor);
    /**
     * @param name - имя удаляемого сенсора
     * @return результат удаления
     */
    boolean delete(String name);

}


