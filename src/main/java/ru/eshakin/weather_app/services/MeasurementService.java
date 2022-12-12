package ru.eshakin.weather_app.services;

import ru.eshakin.weather_app.models.entity.Measurement;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для взаимодействия с репозиторием Measurement
 */
public interface MeasurementService {
    /**
     * Получить все измерения
     * @return список измерений
     */
    List<Measurement> findAll();

    /**
     * Получить все измерения сенсора
     * @param sensorName - имя сенсора
     * @throws ru.eshakin.weather_app.exceptions.BadRequestException если стакого сенсора не существует
     * @return  все измерения конкретного сенсора
     */
    List<Measurement> findBySensorName(String sensorName);
    /**
     * Добавить измерение
     * @param measurement - Добавляемое измерение
     * @return добавленное измерение - если успешно, иначе - пустой Optional
     */
    Optional<Measurement> create(Measurement measurement);

    /**
     * Удалить измерение
     * @param id - ID удаляемого измерения
     * @return - удаленный из БД объект
     */
    Optional<Measurement> delete(int id);

    /**
     * @return Количество дождливых дней
     */
    int getRainyDaysCount();

    /**
     * Добавить множество измерений
     * @param measurements - Добавляемый список измерений
     * @return список добавленных измерений
     */
    List<Measurement> batchCreate(List<Measurement> measurements);
}
