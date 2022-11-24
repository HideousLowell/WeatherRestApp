package ru.eshakin.weather_app.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.eshakin.weather_app.facade.SensorFacade;
import ru.eshakin.weather_app.models.dto.SensorDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sensors")
public class SensorController {

    private final SensorFacade sensorFacade;

    @PostMapping("registration")
    public ResponseEntity<HttpStatus> create(@RequestBody @Validated SensorDto dto) {
        sensorFacade.create(dto);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> delete(@RequestBody SensorDto dto) {
        sensorFacade.delete(dto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}