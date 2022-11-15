package ru.eshakin.WeatherRestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eshakin.WeatherRestApp.models.entity.Measurement;
import ru.eshakin.WeatherRestApp.models.entity.Sensor;
import ru.eshakin.WeatherRestApp.repositories.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    public Measurement findOne(int id) {
        Optional<Measurement> foundMeasurement = measurementRepository.findById(id);
        return foundMeasurement.orElse(null);
    }

    @Transactional
    public void save(Measurement measurement) {
        measurementRepository.save(measurement);
    }

    @Transactional
    public void delete(int id) {
        measurementRepository.deleteById(id);
    }
}
