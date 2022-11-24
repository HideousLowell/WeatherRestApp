package ru.eshakin.weather_app.facade;

import ru.eshakin.weather_app.models.dto.MeasurementDto;
import ru.eshakin.weather_app.models.dto.MeasurementList;

import java.util.List;

public interface MeasurementFacade {
     List<MeasurementDto> findAll();

     List<MeasurementDto> findBySensor(String sensorName);
     void create(MeasurementDto measurement);
     int getRainyDaysCount();
     void delete(int id);
     void batchCreate(MeasurementList listOfDto);
}
