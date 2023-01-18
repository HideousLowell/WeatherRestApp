package ru.eshakin.weather_app.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.eshakin.weather_app.models.dto.SensorDto;
import ru.eshakin.weather_app.repositories.SensorRepo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SensorControllerTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SensorRepo repository;
    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }

    @Test
    public void addSensorTest() throws Exception {
        SensorDto sensor = new SensorDto("R2D22");
        mockMvc.perform(post("/sensors/registration")
                                .content(objectMapper.writeValueAsString(sensor))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteSensorTest() throws Exception {
        SensorDto sensor = new SensorDto("R2D23");
        mockMvc.perform(post("/sensors/registration")
                        .content(objectMapper.writeValueAsString(sensor))
                        .contentType(MediaType.APPLICATION_JSON));
        mockMvc.perform(delete("/sensors")
                        .content(objectMapper.writeValueAsString(sensor))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}