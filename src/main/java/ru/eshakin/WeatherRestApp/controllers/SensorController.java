package ru.eshakin.WeatherRestApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.eshakin.WeatherRestApp.models.dto.SensorDTO;
import ru.eshakin.WeatherRestApp.models.entity.Sensor;
import ru.eshakin.WeatherRestApp.services.SensorService;
import ru.eshakin.WeatherRestApp.util.ErrorResponse;
import ru.eshakin.WeatherRestApp.util.exceptions.SensorNotCreatedException;
import ru.eshakin.WeatherRestApp.util.exceptions.SensorNotFoundException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;

    @GetMapping()
    public List<SensorDTO> getAll() {
        return sensorService.findAll().stream().map(sensorService::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{name}")
    public SensorDTO getOne(@PathVariable("name") String name) {
        Sensor sensor = sensorService.find(name);
        return sensorService.convertToDTO(sensor);
    }

    @PostMapping("registration")
    public ResponseEntity<HttpStatus> create(@RequestBody @Validated SensorDTO sensorDTO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }

            throw new SensorNotCreatedException(errorMsg.toString());
        }

        sensorService.save(sensorService.convertToEntity(sensorDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(SensorNotFoundException ignore) {
        ErrorResponse response = new ErrorResponse("Sensor with this name wasn't found", new Date());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(SensorNotCreatedException exception) {
        ErrorResponse response = new ErrorResponse(exception.getMessage(), new Date());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
