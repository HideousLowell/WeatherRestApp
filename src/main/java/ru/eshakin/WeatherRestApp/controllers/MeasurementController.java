package ru.eshakin.WeatherRestApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.eshakin.WeatherRestApp.models.dto.MeasurementDTO;
import ru.eshakin.WeatherRestApp.models.dto.MeasurementResponse;
import ru.eshakin.WeatherRestApp.services.MeasurementService;
import ru.eshakin.WeatherRestApp.services.SensorService;
import ru.eshakin.WeatherRestApp.util.ErrorResponse;
import ru.eshakin.WeatherRestApp.util.ErrorsUtil;
import ru.eshakin.WeatherRestApp.util.exceptions.MeasurementException;
import ru.eshakin.WeatherRestApp.util.exceptions.SensorNotFoundException;

import java.util.Date;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;
    private final SensorService sensorService;

    @GetMapping()
    public MeasurementResponse getAll() {
        return new MeasurementResponse(measurementService
                .findAll()
                .stream()
                .map(measurementService::convertToDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/rainydayscount")
    public int getRainyDaysCount() {
        return measurementService.getRainyDaysCount();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody @Validated MeasurementDTO dto,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            ErrorsUtil.returnErrorsToClient(bindingResult);

        sensorService.find(dto.getSensor().getName()); // throws if sensor not found
        var measurement = measurementService.convertToEntity(dto);
        measurementService.save(measurement);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(MeasurementException ex) {
        ErrorResponse response = new ErrorResponse(ex.getMessage(), new Date());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(SensorNotFoundException exception) {
        ErrorResponse response = new ErrorResponse("Sensor with this name not found", new Date());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
