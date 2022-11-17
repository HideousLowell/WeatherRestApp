package ru.eshakin.WeatherRestApp.services;

import org.springframework.transaction.annotation.Transactional;
import ru.eshakin.WeatherRestApp.models.entity.Measurement;

import java.util.List;

public interface MeasurementService {

    public List<Measurement> findAll();

    @Transactional
    public void create(Measurement measurement);

    @Transactional
    public void delete(int id);

    public int getRainyDaysCount();
}
