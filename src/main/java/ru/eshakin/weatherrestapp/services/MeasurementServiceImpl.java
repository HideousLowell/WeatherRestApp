package ru.eshakin.weatherrestapp.services;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eshakin.weatherrestapp.models.entity.Measurement;
import ru.eshakin.weatherrestapp.models.entity.Sensor;
import ru.eshakin.weatherrestapp.repositories.MeasurementRepo;
import ru.eshakin.weatherrestapp.util.exceptions.BadRequestException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MeasurementServiceImpl implements MeasurementService {

    private final MeasurementRepo measurementRepo;
    private final SensorService sensorService;

    @Override
    public List<Measurement> findAll() {
        return (List<Measurement>) measurementRepo.findAll();
    }

    @Override
    public void create(Measurement measurement) {
        measurementRepo.save(measurement);
    }

    @Override
    public boolean delete(int id) {
        Optional<Measurement> measurement = measurementRepo.findById(id);
        measurement.ifPresent(measurementRepo::delete);
        return measurement.isPresent();
    }
    Integer a;
    @Override
    public int getRainyDaysCount() {
        return measurementRepo.countByRainingIsTrue();
    }

    @Override
    public void batchCreate(List<Measurement> measurements) {
        measurementRepo.saveAll(measurements);
    }

    public List<Measurement> findBySensorName(String sensorName) {
        Optional<Sensor> sensor = sensorService.find(sensorName);

        if (sensor.isEmpty())
            throw new BadRequestException("Sensor with this name not found");

        Hibernate.initialize(sensor.get().getMeasurements());
        return sensor.get().getMeasurements();
    }

}