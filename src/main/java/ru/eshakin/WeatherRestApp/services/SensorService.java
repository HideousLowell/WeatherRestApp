package ru.eshakin.WeatherRestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eshakin.WeatherRestApp.models.entity.Sensor;
import ru.eshakin.WeatherRestApp.repositories.SensorRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public List<Sensor> findAll() {
        return sensorRepository.findAll();
    }

    public Sensor findOne(int id) {
        Optional<Sensor> foundSensor = sensorRepository.findById(id);
        return foundSensor.orElse(null);
    }

    @Transactional
    public void save(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    @Transactional
    public void delete(String sensorName) {
        sensorRepository.deleteByName(sensorName);
    }
}
