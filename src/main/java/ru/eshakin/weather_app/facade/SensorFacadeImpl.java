package ru.eshakin.weather_app.facade;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.eshakin.weather_app.exceptions.BadRequestException;
import ru.eshakin.weather_app.models.dto.SensorDto;
import ru.eshakin.weather_app.models.entity.Sensor;
import ru.eshakin.weather_app.services.SensorService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class SensorFacadeImpl implements SensorFacade {

    private final SensorService sensorService;
    private final ModelMapper mapper;

    @Override
    public SensorDto create(SensorDto dto) {
        sensorService
                .create(convertToEntity(dto))
                .orElseThrow(() -> new BadRequestException(dto.getName() + " already exist"));
        return dto;
    }

    @Override
    public SensorDto delete(SensorDto dto) {
        if (!sensorService.delete(dto.getName()))
            throw new BadRequestException("Sensor not found");
        return dto;
    }

    @Override
    public List<SensorDto> findAll() {
        return sensorService
                .findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SensorDto> find(String name) {
        return sensorService
                .find(name)
                .stream()
                .map(this::convertToDto)
                .findFirst();
    }

    private SensorDto convertToDto(Sensor sensor) {
        return mapper.map(sensor, SensorDto.class);
    }

    private Sensor convertToEntity(SensorDto dto) {
        return mapper.map(dto, Sensor.class);
    }
}
