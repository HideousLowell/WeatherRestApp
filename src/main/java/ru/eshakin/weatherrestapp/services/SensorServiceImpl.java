package ru.eshakin.weatherrestapp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eshakin.weatherrestapp.models.entity.Sensor;
import ru.eshakin.weatherrestapp.repositories.SensorRepo;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SensorServiceImpl implements SensorService{

    private final SensorRepo sensorRepo;

    @Override
    public List<Sensor> findAll() {
        return (List<Sensor>) sensorRepo.findAll();
    }

    @Override
    public Optional<Sensor> find(String name) {
        return sensorRepo.findByName(name);
    }

    @Override
    public Optional<Sensor> create(Sensor sensor)  {
        if (sensorRepo.findByName(sensor.getName()).isPresent())
            return Optional.empty();

        return Optional.of(sensorRepo.save(sensor));
    }

    @Override
    public boolean delete(String name) {
        Optional<Sensor> sensor = sensorRepo.findByName(name);
        sensor.ifPresent(sensorRepo::delete);
        return sensor.isPresent();
    }
}
