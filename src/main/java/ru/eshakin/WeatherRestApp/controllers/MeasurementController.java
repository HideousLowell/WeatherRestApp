package ru.eshakin.WeatherRestApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.eshakin.WeatherRestApp.facade.MeasurementFacade;
import ru.eshakin.WeatherRestApp.models.dto.MeasurementDto;
import ru.eshakin.WeatherRestApp.models.dto.MeasurementList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementFacade mFacade;

    @GetMapping()
    public MeasurementList getAll() {
        return new MeasurementList(mFacade.findAll());
    }

    @GetMapping("{sensorName}")
    public MeasurementList getForSensor(@PathVariable String sensorName) {
        return new MeasurementList(mFacade.findBySensor(sensorName));
    }

    @GetMapping("/rainydayscount")
    public int getRainyDaysCount() {
        return mFacade.getRainyDaysCount();
    }

    @PostMapping("/add/batch")
    public ResponseEntity<HttpStatus> createBatch(@RequestBody @Validated MeasurementList listOfDto) {
        mFacade.batchCreate(listOfDto);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> batchCreate(@RequestBody @Validated MeasurementDto dto) {
        mFacade.create(dto);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

}
