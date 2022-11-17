package ru.eshakin.WeatherRestApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.eshakin.WeatherRestApp.facade.MeasurementFacade;
import ru.eshakin.WeatherRestApp.facade.SensorFacade;
import ru.eshakin.WeatherRestApp.models.dto.MeasurementDto;
import ru.eshakin.WeatherRestApp.models.dto.MeasurementResponse;
import ru.eshakin.WeatherRestApp.services.MeasurementService;
import ru.eshakin.WeatherRestApp.services.SensorService;
import ru.eshakin.WeatherRestApp.util.ApiError;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementFacade mFacade;
    private final SensorFacade sFacade;

    @GetMapping()
    public MeasurementResponse getAll() {
        return new MeasurementResponse(mFacade.findAll());
    }

    @GetMapping("/rainydayscount")
    public int getRainyDaysCount() {
        return mFacade.getRainyDaysCount();
    }

    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody @Validated MeasurementDto dto) {

        if (sFacade.find(dto.getSensor().getName()).isEmpty())
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiError("Sensor with this name not found"));

        mFacade.create(dto);

        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
