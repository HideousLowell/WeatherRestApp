package ru.eshakin.WeatherRestApp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eshakin.WeatherRestApp.models.entity.Sensor;
import ru.eshakin.WeatherRestApp.repositories.SensorRepo;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SensorServiceImpl implements SensorService{

    private final SensorRepo sensorRepo;

    public List<Sensor> findAll() {
        return (List<Sensor>) sensorRepo.findAll();
    }

    public Optional<Sensor> find(String name) {
        return sensorRepo.findByName(name);
    }

    @Transactional
    public Optional<Sensor> create(Sensor sensor)  {
        if (sensorRepo.findByName(sensor.getName()).isPresent())
            return Optional.empty();

        return Optional.of(sensorRepo.save(sensor));
    }

    @Transactional
    public boolean delete(String name) {
        Optional<Sensor> sensor = sensorRepo.findByName(name);
        sensor.ifPresent(sensorRepo::delete);
        return sensor.isPresent();
    }
}
