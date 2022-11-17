package ru.eshakin.WeatherRestApp.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eshakin.WeatherRestApp.models.dto.MeasurementDto;
import ru.eshakin.WeatherRestApp.models.entity.Measurement;
import ru.eshakin.WeatherRestApp.repositories.MeasurementRepo;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MeasurementService {

    private final MeasurementRepo measurementRepository;
    private final ModelMapper mapper;

    public List<Measurement> findAll() {
        return (List<Measurement>) measurementRepository.findAll();
    }

    @Transactional
    public void save(Measurement measurement) {
        measurementRepository.save(measurement);
    }

    @Transactional
    public void save(MeasurementDto measurement) {
        measurementRepository.save(convertToEntity(measurement));
    }

    @Transactional
    public void delete(int id) {
        measurementRepository.deleteById(id);
    }

    public MeasurementDto convertToDTO(Measurement measurement) {
        return mapper.map(measurement, MeasurementDto.class);
    }

    public Measurement convertToEntity(MeasurementDto measurementDTO) {
        return mapper.map(measurementDTO, Measurement.class);
    }

    public int getRainyDaysCount() {
        return measurementRepository.countByRainingIsTrue();
    }
}
