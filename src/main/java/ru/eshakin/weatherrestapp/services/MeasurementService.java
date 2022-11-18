package ru.eshakin.weatherrestapp.services;

import ru.eshakin.weatherrestapp.models.entity.Measurement;

import java.util.List;

public interface MeasurementService {
    List<Measurement> findAll();
    List<Measurement> findBySensorName(String sensorName);
    void create(Measurement measurement);
    boolean delete(int id);
    int getRainyDaysCount();
    void batchCreate(List<Measurement> measurements);
}
