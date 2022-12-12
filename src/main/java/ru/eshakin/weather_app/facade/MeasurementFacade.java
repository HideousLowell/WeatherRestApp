package ru.eshakin.weather_app.facade;

import ru.eshakin.weather_app.models.dto.MeasurementDto;
import ru.eshakin.weather_app.models.dto.MeasurementList;

import java.util.List;

/**
 * Интерфейс, посредством которого контроллер взаимодействовует
 * с сервисом для обращения к БД (таблица Measurement)
 * Преобразует DTO в сущность и обратно
 */
public interface MeasurementFacade {

     /**
      * @return список всех измерений
      */
     List<MeasurementDto> findAll();

     /**
      * @param sensorName - имя сенсора
      * @throws ru.eshakin.weather_app.exceptions.BadRequestException
      * в случае отсуствия искомого сенсора в БД
      * @return список всех измерений для конкретного сенсора
      */
     List<MeasurementDto> findBySensor(String sensorName);

     /**
      * @param measurement - добавляемое измерение
      * @throws ru.eshakin.weather_app.exceptions.BadRequestException
      * * в случае отсуствия сенсора в БД
      * @return измерение в случае успеха
      */
     MeasurementDto create(MeasurementDto measurement);

     /**
      * @return количество дождливых дней
      */
     int getRainyDaysCount();

     /**
      * @param id id удаляемого измерения
      * @throws ru.eshakin.weather_app.exceptions.BadRequestException
      * в случае отсутствия измерения с указанным id в БД
      * @return id удаленного измерения в случае успеха
      */
     int delete(int id);

     /**
      * @param listOfDto список измерений, которые нужно добавить в БД
      * @throws ru.eshakin.weather_app.exceptions.BadRequestException
      * в случае отсутствия какого-либо сенсора в БД, добавление не осуществляется
      * @return список в случае успеха
      */
     MeasurementList addAll(MeasurementList listOfDto);
}
