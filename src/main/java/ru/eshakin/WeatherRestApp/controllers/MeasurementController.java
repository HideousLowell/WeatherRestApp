package ru.eshakin.WeatherRestApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.eshakin.WeatherRestApp.models.dto.MeasurementDto;
import ru.eshakin.WeatherRestApp.models.dto.MeasurementResponse;
import ru.eshakin.WeatherRestApp.services.MeasurementService;
import ru.eshakin.WeatherRestApp.services.SensorService;

import javax.validation.Valid;
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
    public ResponseEntity<?> create(@RequestBody @Valid MeasurementDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            ErrorsUtil.returnErrorsToClient(bindingResult);

        if (sensorService.find(dto.getSensor().getName()).isEmpty())
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiErrorResponse("Sensor with this name not found"));

        measurementService.save(dto);

        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
