package ru.eshakin.weather_app.facade;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.eshakin.weather_app.models.dto.SensorDto;
import ru.eshakin.weather_app.models.entity.Sensor;
import ru.eshakin.weather_app.services.SensorService;
import ru.eshakin.weather_app.exceptions.BadRequestException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class SensorFacadeImpl implements SensorFacade {

    private final SensorService sensorService;
    private final ModelMapper mapper;

    public void create(SensorDto dto) {
        Sensor sensor = mapper.map(dto, Sensor.class);
        sensorService
                .create(sensor)
                .orElseThrow(() -> new BadRequestException(sensor.getName() + " already exist"));
    }

    public void delete(SensorDto dto) {
        if (!sensorService.delete(dto.getName()))
            throw new BadRequestException("Sensor not found");
    }

    public List<Sensor> findAll() {
        return sensorService.findAll();
    }

    public Optional<Sensor> find(String name) {
        return sensorService.find(name);
    }

}
