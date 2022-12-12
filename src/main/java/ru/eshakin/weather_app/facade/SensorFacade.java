package ru.eshakin.weather_app.facade;

import ru.eshakin.weather_app.exceptions.BadRequestException;
import ru.eshakin.weather_app.models.dto.SensorDto;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс, посредством которого контроллер взаимодействовует
 * с сервисом для работы с таблицей Sensor
 * Преобразует DTO в сущность и обратно
 */
public interface SensorFacade {

     /**
      * @param dto сохраняемый сенсор
      * @throws BadRequestException если сенсор с данным именем уже существует
      * @return сохраненный сенсор
      */
     SensorDto create(SensorDto dto);

     /**
      * @param dto удаляемый сенсор
      * @throws BadRequestException если сенсор не найден
      * @return удаленный сенсор
      */
     SensorDto delete(SensorDto dto);

     /**
      * @return список всех сенсоров
      */
     List<SensorDto> findAll();

     /**
      * @param name имя искомого сенсора
      * @return найденный сенсор, или пустой optional
      */
     Optional<SensorDto> find(String name);
}
