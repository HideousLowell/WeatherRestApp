package ru.eshakin.weather_app.services;

import ru.eshakin.weather_app.models.entity.Measurement;

import java.util.List;

/**
 * Сервис для взаимодействия с репозиторием Measurement
 */
public interface MeasurementService {
    /**
     * @return все измерения
     */
    List<Measurement> findAll();

    /**
     * @param sensorName - имя сенсора
     * @return  все измерения конкретного сенсора
     */
    List<Measurement> findBySensorName(String sensorName);
    /**
     * Ничего не возвращает в связи  отсутствием ограничений по уникальности
     * @param measurement - Добавляемое измерение
     */
    void create(Measurement measurement);

    /**
     * @param id - ID удаляемого измерения
     * @return - результат удаления
     */
    boolean delete(int id);

    /**
     * @return Количество дождливых дней
     */
    int getRainyDaysCount();

    /**
     * @param measurements - Добавляемый список измерений
     */
    void batchCreate(List<Measurement> measurements);
}
