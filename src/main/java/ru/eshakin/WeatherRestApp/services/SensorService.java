package ru.eshakin.WeatherRestApp.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eshakin.WeatherRestApp.models.dto.SensorDTO;
import ru.eshakin.WeatherRestApp.models.entity.Sensor;
import ru.eshakin.WeatherRestApp.repositories.SensorRepository;
import ru.eshakin.WeatherRestApp.util.exceptions.SensorNotCreatedException;
import ru.eshakin.WeatherRestApp.util.exceptions.SensorNotFoundException;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SensorService {

    private final SensorRepository sensorRepository;
    private final ModelMapper modelMapper;

    public List<Sensor> findAll() {
        return sensorRepository.findAll();
    }

    public Sensor find(String name) throws SensorNotFoundException {
        return sensorRepository.findByName(name).orElseThrow(SensorNotFoundException::new);
    }

    @Transactional
    public void save(Sensor sensor) {
        var foundSensor = sensorRepository.findByName(sensor.getName());
        foundSensor.ifPresent(s -> {
            throw new SensorNotCreatedException("Sensor with this name is already in the database");
        });

        sensorRepository.save(sensor);
    }

    @Transactional
    public void delete(String sensorName) {
        sensorRepository.deleteByName(sensorName);
    }

    public SensorDTO convertToDTO(Sensor sensor) {
        return modelMapper.map(sensor, SensorDTO.class);
    }

    public Sensor convertToEntity(SensorDTO sensorDto) {
        return modelMapper.map(sensorDto, Sensor.class);
    }
}
