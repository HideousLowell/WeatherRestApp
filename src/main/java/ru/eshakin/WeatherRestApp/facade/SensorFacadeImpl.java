package ru.eshakin.WeatherRestApp.facade;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.eshakin.WeatherRestApp.models.dto.SensorDto;
import ru.eshakin.WeatherRestApp.models.entity.Sensor;
import ru.eshakin.WeatherRestApp.services.SensorService;
import ru.eshakin.WeatherRestApp.util.exceptions.BadRequestException;

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
