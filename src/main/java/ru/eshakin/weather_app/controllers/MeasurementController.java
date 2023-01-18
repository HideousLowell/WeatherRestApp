package ru.eshakin.weather_app.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.eshakin.weather_app.facade.MeasurementFacade;
import ru.eshakin.weather_app.models.dto.MeasurementDto;
import ru.eshakin.weather_app.models.dto.MeasurementList;

/**
 * Api клиента для работы с измерениями
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementFacade mFacade;

    /**
     * Получить все измерения
     * @return список измерений
     */
    @GetMapping()
    public MeasurementList getAll() {
        return new MeasurementList(mFacade.findAll());
    }

    /**
     * Получить все измерения для определенного сенсора
     * @param sensorName имя сенсора
     * @return список измерений для сенсора
     */
    @GetMapping("{sensorName}")
    public MeasurementList getForSensor(@PathVariable String sensorName) {
        return new MeasurementList(mFacade.findBySensor(sensorName));
    }

    /**
     * Получить количество дождливых дней
     * @return количество дней
     */
    @GetMapping("/rainydayscount")
    public int getRainyDaysCount() {
        return mFacade.getRainyDaysCount();
    }

    /**
     * Добавить список измерений в БД
     * @param measurements список измерений
     * @return HttpStatus.CREATED в случае успеха
     */
    @PostMapping("/add/batch")
    public ResponseEntity<HttpStatus> createBatch(@RequestBody @Validated MeasurementList measurements) {
        mFacade.batchCreate(measurements);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    /**
     * Добавить измерение в БД
     * @param measurement измерений
     * @return HttpStatus.CREATED в случае успеха
     */
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody @Validated MeasurementDto measurement) {
        mFacade.create(measurement);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

}
