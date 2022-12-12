package ru.eshakin.weather_app.facade;

import ru.eshakin.weather_app.models.dto.MeasurementDto;
import ru.eshakin.weather_app.models.dto.MeasurementList;

import java.util.List;

/**
 * Интерфейс, посредством которого контроллер взаимодействовует
 * с сервисом для обращения к БД (таблица Measurement)
 * Обрабатывает возникшие ошибки, выбрасывая исключения
 * Преобразует DTO в сущность, передает ее сервису (и обратно)
 */
public interface MeasurementFacade {

     /**
      * Получить все измерения
      * @return список всех измерений
      */
     List<MeasurementDto> findAll();

     /**
      * Найти все измерения определенного сенсора
      * @param sensorName - имя сенсора
      * @return список всех измерений для конкретного сенсора
      */
     List<MeasurementDto> findBySensor(String sensorName);

     /**
      * Добавить измерение
      * @param measurement - добавляемое измерение
      * @throws ru.eshakin.weather_app.exceptions.BadRequestException
      * * в случае отсуствия сенсора в БД
      * @return измерение в случае успеха
      */
     MeasurementDto create(MeasurementDto measurement);

     /**
      * Получить количество дождливых дней
      * @return количество дней
      */
     int getRainyDaysCount();

     /**
      * Удалить измерение
      * @param id id удаляемого измерения
      * @throws ru.eshakin.weather_app.exceptions.BadRequestException
      * в случае отсутствия измерения с указанным id в БД
      * @return id удаленного измерения в случае успеха
      */
     int delete(int id);

     /**
      * Добавить список измерений
      * @param listOfDto список измерений, которые нужно добавить в БД
      * @throws ru.eshakin.weather_app.exceptions.BadRequestException
      * в случае отсутствия какого-либо сенсора в БД, добавление не осуществляется
      * @return список в случае успеха
      */
     MeasurementList batchCreate(MeasurementList listOfDto);
}
