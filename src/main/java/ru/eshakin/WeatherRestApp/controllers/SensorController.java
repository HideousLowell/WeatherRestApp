package ru.eshakin.WeatherRestApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.eshakin.WeatherRestApp.models.dto.SensorDTO;
import ru.eshakin.WeatherRestApp.models.entity.Sensor;
import ru.eshakin.WeatherRestApp.services.SensorService;
import ru.eshakin.WeatherRestApp.util.SensorNotCreatedException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;

    @Autowired
    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping()
    public List<Sensor> getPeople() {
        return sensorService.findAll();
    }

    @GetMapping("/{id}")
    public Sensor getPerson(@PathVariable("id") int id) {
        return sensorService.findOne(id);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SensorDTO sensorDTO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors)
                errorMsg.append(error.toString());

            throw new SensorNotCreatedException(errorMsg.toString());

        }

        sensorService.save(new Sensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
