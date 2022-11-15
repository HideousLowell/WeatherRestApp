package ru.eshakin.WeatherRestApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.eshakin.WeatherRestApp.models.dto.SensorDTO;
import ru.eshakin.WeatherRestApp.models.entity.Measurement;
import ru.eshakin.WeatherRestApp.models.entity.Sensor;
import ru.eshakin.WeatherRestApp.services.MeasurementService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;

    @Autowired
    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @GetMapping()
    public List<Measurement> getMeasurements() {
        return measurementService.findAll();
    }

    @GetMapping("/{id}")
    public Measurement getMeasurement(@PathVariable("id") int id) {
        return measurementService.findOne(id);
    }


}
