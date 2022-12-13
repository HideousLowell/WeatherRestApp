package ru.eshakin.weather_app.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.eshakin.weather_app.exceptions.BadRequestException;
import ru.eshakin.weather_app.models.entity.Measurement;
import ru.eshakin.weather_app.models.entity.Sensor;
import ru.eshakin.weather_app.repositories.MeasurementRepo;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MeasurementServiceImplTest {

    private MeasurementRepo measurementRepo;
    private SensorService sensorService;
    private MeasurementServiceImpl measurementService;

    private final Sensor SENSOR = new Sensor("R2D2", null);
    private final Measurement MEASUREMENT1 = new Measurement(1, 20.3, true, new Date(), SENSOR);
    private final Measurement MEASUREMENT2 = new Measurement(2, 25.3, false, new Date(), SENSOR);
    private final List<Measurement> MEASUREMENTS = List.of(MEASUREMENT1, MEASUREMENT2);
    private final List<Measurement> EMPTY_LIST = Collections.emptyList();
    private final Optional<Measurement> OPT_MEASUREMENT1 = Optional.of(MEASUREMENT1);

    {
        SENSOR.setMeasurements(MEASUREMENTS);
    }

    @BeforeEach
    public void init() {
        measurementRepo = Mockito.mock(MeasurementRepo.class);
        sensorService = Mockito.mock(SensorServiceImpl.class);
        measurementService = new MeasurementServiceImpl(measurementRepo, sensorService);
    }

    @Test
    void findAllExpectEqual() {
        Mockito.when(measurementRepo.findAll()).thenReturn(MEASUREMENTS);
        assertEquals(MEASUREMENTS, measurementService.findAll());
        Mockito.verify(measurementRepo).findAll();
    }

    @Test
    void findAllExpectEqualWhenEmpty() {
        Mockito.when(measurementRepo.findAll()).thenReturn(EMPTY_LIST);
        assertEquals(EMPTY_LIST, measurementService.findAll());
    }

    @Test
    void findAllExpectNotEqual() {
        Mockito.when(measurementRepo.findAll()).thenReturn(MEASUREMENTS);
        assertNotEquals(List.of(MEASUREMENT2), measurementService.findAll());
    }

    @Test
    void createExpectTrue() {
        Mockito.when(sensorService.find(SENSOR.getName())).thenReturn(Optional.of(SENSOR));
        Mockito.when(measurementRepo.save(MEASUREMENT1)).thenReturn(MEASUREMENT1);
        assertEquals(OPT_MEASUREMENT1, measurementService.create(MEASUREMENT1));
        Mockito.verify(sensorService).find(MEASUREMENT1.getSensor().getName());
    }

    @Test
    void createWhenSensorDoesntExist() {
        Mockito.when(sensorService.find(SENSOR.getName())).thenReturn(Optional.empty());
        assertEquals(Optional.empty(),measurementService.create(MEASUREMENT1));
        Mockito.verify(sensorService).find(MEASUREMENT1.getSensor().getName());
    }

    @Test
    void deleteExpectTrue() {
        Mockito.when(measurementRepo.findById(MEASUREMENT1.getId())).thenReturn(OPT_MEASUREMENT1);
        assertEquals(OPT_MEASUREMENT1, measurementService.delete(MEASUREMENT1.getId()));
    }

    @Test
    void deleteWhenDoesntExist() {
        Mockito.when(measurementRepo.findById(MEASUREMENT1.getId())).thenReturn(Optional.empty());
        assertEquals(Optional.empty(), measurementService.delete(MEASUREMENT1.getId()));
    }

    @Test
    void getRainyDaysExpectEquals() {
        Mockito.when(measurementRepo.countByRainingIsTrue()).thenReturn(5);
        assertEquals(5, measurementService.getRainyDaysCount());
    }

    @Test
    void getRainyDaysExpectNotEquals() {
        Mockito.when(measurementRepo.countByRainingIsTrue()).thenReturn(0);
        assertNotEquals(1, measurementService.getRainyDaysCount());
    }

    @Test
    void batchCreateExpectEqual() {
        assertEquals(MEASUREMENTS, measurementService.batchCreate(MEASUREMENTS));
    }

    @Test
    void batchCreateExpectNotEqual() {
        assertNotEquals(Collections.emptyList(), measurementService.batchCreate(MEASUREMENTS));
    }

    @Test
    void findBySensorNameExpectOkay() {
        Mockito.when(sensorService.find(SENSOR.getName())).thenReturn(Optional.of(SENSOR));
        assertEquals(MEASUREMENTS, measurementService.findBySensorName(SENSOR.getName()));
    }

    @Test
    void findBySensorExpectThrow() {
        Mockito.when(sensorService.find(SENSOR.getName())).thenThrow(BadRequestException.class);
        assertThrows(BadRequestException.class, () -> measurementService.findBySensorName(SENSOR.getName()));
    }
}