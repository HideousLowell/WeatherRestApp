package ru.eshakin.weather_app.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.eshakin.weather_app.models.dto.MeasurementDto;
import ru.eshakin.weather_app.models.dto.SensorDto;
import ru.eshakin.weather_app.repositories.MeasurementRepo;
import ru.eshakin.weather_app.repositories.SensorRepo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MeasurementControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SensorRepo sensorRepo;

    @Autowired
    private MeasurementRepo measurementRepo;

    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    public void resetDb() {
        sensorRepo.deleteAll();
        measurementRepo.deleteAll();
    }

    public void addSensor(SensorDto sensor) throws Exception {
        mockMvc.perform(post("/sensors/registration")
                .content(objectMapper.writeValueAsString(sensor))
                .contentType(MediaType.APPLICATION_JSON));
    }

    public void addMeasurementExpectOk(MeasurementDto measurement) throws Exception {
        mockMvc.perform(post("/measurements/add")
                        .content(objectMapper.writeValueAsString(measurement))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAll() {

    }

    @Test
    void getForSensor() {
    }

    @Test
    void getRainyDaysCount() {
    }

    @Test
    void createBatch() {
    }

    @Test
    void create() throws Exception {
        SensorDto sensorDto = new SensorDto("first");
        addSensor(sensorDto);
        addMeasurementExpectOk(new MeasurementDto(21.1, false, sensorDto));
        addMeasurementExpectOk(new MeasurementDto(22.2, true, sensorDto));
        addMeasurementExpectOk(new MeasurementDto(23.3, false, sensorDto));
    }
}