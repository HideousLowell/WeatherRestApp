package ru.eshakin.weather_app.services;

import ru.eshakin.weather_app.models.entity.Sensor;

import java.util.List;
import java.util.Optional;

/**
 *  * Сервис для взаимодействия с репозиторием Sensor
 */
public interface SensorService {
    /**
     * Найти все сенсоры
     * @return список всех сенсоров
     */
    List<Sensor> findAll();
    /**
     * Найти сенсор
     * @param name - имя искомого сенсора
     * @return найденный сенсор, или пустой Optional в случае неудачи
     */
    Optional<Sensor> find(String name);
    /**
     * Добавить сенсор
     * @param sensor - Добавляемый в БД сенсор
     * @return созданный сенсор, или пустой Optional в случае неудачи
     */
    Optional<Sensor> create(Sensor sensor);
    /**
     * Удалить сенсор
     * @param name - имя удаляемого сенсора
     * @return удаленный сенсор, или пустой Optional в случае неудачи
     */
    Optional<Sensor> delete(String name);

}


