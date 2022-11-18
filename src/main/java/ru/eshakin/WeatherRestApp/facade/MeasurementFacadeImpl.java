package ru.eshakin.WeatherRestApp.facade;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.eshakin.WeatherRestApp.models.dto.MeasurementDto;
import ru.eshakin.WeatherRestApp.models.dto.MeasurementList;
import ru.eshakin.WeatherRestApp.models.entity.Measurement;
import ru.eshakin.WeatherRestApp.services.MeasurementService;
import ru.eshakin.WeatherRestApp.services.SensorService;
import ru.eshakin.WeatherRestApp.util.exceptions.BadRequestException;

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

    @Override
    public int getRainyDaysCount() {
        return measurementService.getRainyDaysCount();
    }

    @Override
    public void create(MeasurementDto dto) {
        if (sensorService.find(dto.getSensor().getName()).isEmpty())
            throw new BadRequestException("Sensor with this name not found");
        measurementService.create(convertToEntity(dto));
    }

    @Override
    public void batchCreate(MeasurementList listOfDto) {

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
    }

    @Override
    public void delete(int id) {
        measurementService.delete(id);
    }

    private MeasurementDto convertToDto(Measurement measurement) {
        return mapper.map(measurement, MeasurementDto.class);
    }

    private Measurement convertToEntity(MeasurementDto measurementDTO) {
        return mapper.map(measurementDTO, Measurement.class);
    }

}
