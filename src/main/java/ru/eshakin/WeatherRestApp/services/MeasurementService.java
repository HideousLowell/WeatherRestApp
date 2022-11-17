package ru.eshakin.WeatherRestApp.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eshakin.WeatherRestApp.models.dto.MeasurementDTO;
import ru.eshakin.WeatherRestApp.models.entity.Measurement;
import ru.eshakin.WeatherRestApp.repositories.MeasurementRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final ModelMapper modelMapper;

    public List<Measurement> findAll() {
        return (List<Measurement>) measurementRepository.findAll();
    }

    @Transactional
    public void save(Measurement measurement) {
        measurementRepository.save(measurement);
    }

    @Transactional
    public void delete(int id) {
        measurementRepository.deleteById(id);
    }

    public MeasurementDTO convertToDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    public Measurement convertToEntity(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    public int getRainyDaysCount() {
        return measurementRepository.countByRainingIsTrue();
    }
}
