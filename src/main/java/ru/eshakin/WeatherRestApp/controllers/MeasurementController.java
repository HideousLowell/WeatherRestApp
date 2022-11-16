package ru.eshakin.WeatherRestApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.eshakin.WeatherRestApp.models.dto.MeasurementDTO;
import ru.eshakin.WeatherRestApp.models.entity.Measurement;
import ru.eshakin.WeatherRestApp.services.MeasurementService;
import ru.eshakin.WeatherRestApp.services.SensorService;
import ru.eshakin.WeatherRestApp.util.ErrorsUtil;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;
    private final SensorService sensorService;

    @GetMapping()
    public List<MeasurementDTO> getAll() {
        return measurementService
                .findAll()
                .stream()
                .map(measurementService::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public MeasurementDTO getOne(@PathVariable("id") int id) {
        Measurement measurement = measurementService.findOne(id);
        return measurementService.convertToDTO(measurement);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody @Validated MeasurementDTO measurementDTO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            ErrorsUtil.returnErrorsToClient(bindingResult);

        sensorService.find(measurementDTO.getSensor().getName()); // throws if sensor not found
        var measurement = measurementService.convertToEntity(measurementDTO);
        measurementService.save(measurement);

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
