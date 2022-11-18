package ru.eshakin.WeatherRestApp.facade;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.eshakin.WeatherRestApp.models.dto.MeasurementDto;
import ru.eshakin.WeatherRestApp.models.entity.Measurement;
import ru.eshakin.WeatherRestApp.services.MeasurementService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class MeasurementFacadeImpl implements MeasurementFacade {

    private final MeasurementService service;
    private final ModelMapper mapper;

    public List<MeasurementDto> findAll() {
        return service.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public int getRainyDaysCount() {
        return service.getRainyDaysCount();
    }

    @Override
    public void create(MeasurementDto dto) {
        service.create(convertToEntity(dto));
    }

    @Override
    public void delete(int id){
        service.delete(id);
    }

    @Override
    public MeasurementDto convertToDto(Measurement measurement) {
        return mapper.map(measurement, MeasurementDto.class);
    }

    @Override
    public Measurement convertToEntity(MeasurementDto measurementDTO) {
        return mapper.map(measurementDTO, Measurement.class);
    }

}
