package ru.eshakin.WeatherRestApp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eshakin.WeatherRestApp.models.entity.Measurement;
import ru.eshakin.WeatherRestApp.repositories.MeasurementRepo;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MeasurementServiceImpl implements MeasurementService {

    private final MeasurementRepo measurementRepo;

    @Override
    public List<Measurement> findAll() {
        return (List<Measurement>) measurementRepo.findAll();
    }

    @Override
    @Transactional
    public void create(Measurement measurement) {
        measurementRepo.save(measurement);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        Optional<Measurement> measurement = measurementRepo.findById(id);
        measurement.ifPresent(measurementRepo::delete);
        return measurement.isPresent();
    }

    @Override
    public int getRainyDaysCount() {
        return measurementRepo.countByRainingIsTrue();
    }
}
