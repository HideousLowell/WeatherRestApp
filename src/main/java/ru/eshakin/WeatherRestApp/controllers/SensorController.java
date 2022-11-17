package ru.eshakin.WeatherRestApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.eshakin.WeatherRestApp.facade.SensorFacade;
import ru.eshakin.WeatherRestApp.models.dto.SensorDto;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sensors")
public class SensorController {

    private final SensorFacade sensorFacade;

    @PostMapping("registration")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SensorDto dto,
                                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            ErrorsUtil.returnErrorsToClient(bindingResult);

        sensorFacade.create(dto);

        return ResponseEntity.ok(HttpStatus.CREATED);

    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> delete(@RequestBody SensorDto dto) {
        sensorFacade.delete(dto);

        return ResponseEntity.ok(HttpStatus.OK);
    }

}
