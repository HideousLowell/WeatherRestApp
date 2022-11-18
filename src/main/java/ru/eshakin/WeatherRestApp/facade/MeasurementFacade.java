package ru.eshakin.WeatherRestApp.facade;

import ru.eshakin.WeatherRestApp.models.dto.MeasurementDto;
import ru.eshakin.WeatherRestApp.models.dto.MeasurementList;

import java.util.List;

public interface MeasurementFacade {
     List<MeasurementDto> findAll();
     void create(MeasurementDto measurement);
     int getRainyDaysCount();
     void delete(int id);
     void batchCreate(MeasurementList listOfDto);
}
