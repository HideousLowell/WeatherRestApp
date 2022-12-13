package ru.eshakin.weather_app.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.eshakin.weather_app.models.entity.Sensor;
import ru.eshakin.weather_app.repositories.SensorRepo;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SensorServiceImplTest {

    private SensorRepo sensorRepository;
    private SensorServiceImpl sensorService;

    private final Sensor SENSOR1 = new Sensor("R2D2", Collections.emptyList());
    private final Sensor SENSOR2 = new Sensor("some_sensor", Collections.emptyList());
    private final List<Sensor> SENSORS = List.of(SENSOR1, SENSOR2);
    private final List<Sensor> EMPTY_LIST = Collections.emptyList();

    @BeforeEach
    public void init() {
        sensorRepository = Mockito.mock(SensorRepo.class);
        sensorService = new SensorServiceImpl(sensorRepository);
    }

    @Test
    void findAllExpectEqual() {
        Mockito.when(sensorRepository.findAll()).thenReturn(SENSORS);
        assertEquals(SENSORS, sensorService.findAll());
        Mockito.verify(sensorRepository).findAll();
    }

    @Test
    void findAllExpectNotEqual() {
        Mockito.when(sensorRepository.findAll()).thenReturn(SENSORS);
        assertNotEquals(List.of(SENSOR1), sensorService.findAll());
    }

    @Test
    void findAllExpectEqualWithEmptyList() {
        Mockito.when(sensorRepository.findAll()).thenReturn(EMPTY_LIST);
        assertEquals(EMPTY_LIST, sensorService.findAll());
    }

    @Test
    void findExpectEqual() {
        Mockito.when(sensorRepository.findByName(SENSOR1.getName())).thenReturn(Optional.of(SENSOR1));
        Optional<Sensor> sensor = sensorService.find(SENSOR1.getName());
        assertTrue(sensor.isPresent());
        assertEquals(SENSOR1, sensorService.find(SENSOR1.getName()).get());
    }

    @Test
    void findExpectNotEqual() {
        Mockito.when(sensorRepository.findByName(SENSOR1.getName())).thenReturn(Optional.of(SENSOR1));
        Optional<Sensor> sensor = sensorService.find(SENSOR1.getName());
        assertTrue(sensor.isPresent());
        assertNotEquals(SENSOR2, sensorService.find(SENSOR1.getName()).get());
    }

    @Test
    void createExpectEquals() {
        Mockito.when(sensorRepository.save(SENSOR1)).thenReturn(SENSOR1);
        Optional<Sensor> sensor = sensorService.create(SENSOR1);
        assertTrue(sensor.isPresent());
        assertEquals(SENSOR1, sensor.get());
    }

    @Test
    void createExpectNotEquals() {
        Mockito.when(sensorRepository.save(SENSOR1)).thenReturn(SENSOR1);
        Optional<Sensor> sensor = sensorService.create(SENSOR1);
        Mockito.verify(sensorRepository).findByName(SENSOR1.getName());
        assertTrue(sensor.isPresent());
        assertNotEquals(SENSOR2, sensor.get());
    }

    @Test
    void createWhenAlreadyExist() {
        Mockito.when(sensorRepository.findByName(SENSOR1.getName())).thenReturn(Optional.of(SENSOR1));
        Optional<Sensor> sensor = sensorService.create(SENSOR1);
        Mockito.verify(sensorRepository).findByName(SENSOR1.getName());
        assertTrue(sensor.isEmpty());
    }

    @Test
    void deleteExpectTrue() {
        Mockito.when(sensorRepository.findByName(SENSOR1.getName())).thenReturn(Optional.of(SENSOR1));
        assertTrue(sensorService.delete(SENSOR1.getName()).isPresent());
        Mockito.verify(sensorRepository).findByName(SENSOR1.getName());
        Mockito.verify(sensorRepository).delete(SENSOR1);
    }

    @Test
    void deleteWhenDoesntExist() {
        Mockito.when(sensorRepository.findByName(SENSOR1.getName())).thenReturn(Optional.empty());
        assertTrue(sensorService.delete(SENSOR1.getName()).isEmpty());
    }
}