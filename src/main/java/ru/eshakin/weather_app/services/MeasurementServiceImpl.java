package ru.eshakin.weather_app.services;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import ru.eshakin.weather_app.exceptions.BadRequestException;
import ru.eshakin.weather_app.models.entity.Measurement;
import ru.eshakin.weather_app.models.entity.Sensor;
import ru.eshakin.weather_app.repositories.MeasurementRepo;

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
    public Optional<Measurement> create(Measurement measurement) {
        if (sensorService.find(measurement.getSensor().getName()).isEmpty())
            return Optional.empty();

        return Optional.of(measurementRepo.save(measurement));
    }

    @Override
    public Optional<Measurement> delete(int id) {
        Optional<Measurement> measurement = measurementRepo.findById(id);
        measurement.ifPresent(measurementRepo::delete);
        return measurement;
    }

    @Override
    public int getRainyDaysCount() {
        return measurementRepo.countByRainingIsTrue();
    }

    @Override
    public List<Measurement> batchCreate(List<Measurement> measurements) {
        measurementRepo.saveAll(measurements);
        return measurements;
    }

    @Override
    public List<Measurement> findBySensorName(String sensorName) {
        Optional<Sensor> sensor = sensorService.find(sensorName);

        if (sensor.isEmpty())
            throw new BadRequestException("Sensor : " + sensorName + " wasn't found");

        Hibernate.initialize(sensor.get().getMeasurements());
        return sensor.get().getMeasurements();
    }

}
