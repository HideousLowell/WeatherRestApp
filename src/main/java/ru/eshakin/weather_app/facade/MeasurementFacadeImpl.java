package ru.eshakin.weather_app.facade;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.eshakin.weather_app.models.dto.MeasurementDto;
import ru.eshakin.weather_app.models.dto.MeasurementList;
import ru.eshakin.weather_app.models.entity.Measurement;
import ru.eshakin.weather_app.services.MeasurementService;
import ru.eshakin.weather_app.services.SensorService;
import ru.eshakin.weather_app.exceptions.BadRequestException;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class MeasurementFacadeImpl implements MeasurementFacade {

    private final MeasurementService measurementService;
    private final SensorService sensorService;
    private final ModelMapper mapper;

    public List<MeasurementDto> findAll() {
        return measurementService
                .findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<MeasurementDto> findBySensor(String sensorName) {
        if (sensorService.find(sensorName).isEmpty())
            throw new BadRequestException("Sensor with this name not found");
        return measurementService
                .findBySensorName(sensorName)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public int getRainyDaysCount() {
        return measurementService.getRainyDaysCount();
    }

    @Override
    public MeasurementDto create(MeasurementDto dto) {
        if (sensorService.find(dto.getSensor().getName()).isEmpty())
            throw new BadRequestException("Sensor with this name not found");
        measurementService.create(convertToEntity(dto));
        return dto;
    }

    @Override
    public MeasurementList addAll(MeasurementList listOfDto) {
        List<Measurement> resultList = listOfDto
                .getMeasurements()
                .stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());

        resultList.forEach(entity -> {
            String sensorName = entity.getSensor().getName();
            if (sensorService.find(sensorName).isEmpty())
                throw new BadRequestException("Sensor with this name not found");
        });

        measurementService.batchCreate(resultList);
        return listOfDto;
    }

    @Override
    public int delete(int id) {
        if (measurementService.delete(id))
            return id;
        throw new BadRequestException("Measurement with this id not found");
    }

    private MeasurementDto convertToDto(Measurement measurement) {
        return mapper.map(measurement, MeasurementDto.class);
    }

    private Measurement convertToEntity(MeasurementDto measurementDTO) {
        return mapper.map(measurementDTO, Measurement.class);
    }

}
