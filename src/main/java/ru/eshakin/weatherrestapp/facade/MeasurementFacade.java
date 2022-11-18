package ru.eshakin.weatherrestapp.facade;

import ru.eshakin.weatherrestapp.models.dto.MeasurementDto;
import ru.eshakin.weatherrestapp.models.dto.MeasurementList;

import java.util.List;

public interface MeasurementFacade {
     List<MeasurementDto> findAll();

     List<MeasurementDto> findBySensor(String sensorName);
     void create(MeasurementDto measurement);
     int getRainyDaysCount();
     void delete(int id);
     void batchCreate(MeasurementList listOfDto);
}
