package ru.eshakin.WeatherRestApp.services;

import ru.eshakin.WeatherRestApp.models.entity.Measurement;
import ru.eshakin.WeatherRestApp.models.entity.Sensor;

import java.util.List;

public interface MeasurementService {
    List<Measurement> findAll();
    List<Measurement> findBySensorName(String sensorName);
    void create(Measurement measurement);
    boolean delete(int id);
    int getRainyDaysCount();
    void batchCreate(List<Measurement> measurements);
}
