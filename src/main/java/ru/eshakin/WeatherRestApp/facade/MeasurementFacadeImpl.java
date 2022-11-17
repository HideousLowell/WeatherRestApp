package ru.eshakin.WeatherRestApp.facade;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import ru.eshakin.WeatherRestApp.models.dto.MeasurementDto;
import ru.eshakin.WeatherRestApp.models.dto.SensorDto;
import ru.eshakin.WeatherRestApp.models.entity.Measurement;
import ru.eshakin.WeatherRestApp.models.entity.Sensor;
import ru.eshakin.WeatherRestApp.services.MeasurementService;
import ru.eshakin.WeatherRestApp.util.exceptions.BadRequestException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class MeasurementFacadeImpl {

    private final MeasurementService service;
    private final ModelMapper mapper;

    public List<MeasurementDto> findAll() {
        return service.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public int getRainyDaysCount() {
        return service.getRainyDaysCount();
    }

    public void create(MeasurementDto dto) {
        service.create(convertToEntity(dto));
    }

    public MeasurementDto convertToDto(Measurement measurement) {
        return mapper.map(measurement, MeasurementDto.class);
    }

    public Measurement convertToEntity(MeasurementDto measurementDTO) {
        return mapper.map(measurementDTO, Measurement.class);
    }

}
