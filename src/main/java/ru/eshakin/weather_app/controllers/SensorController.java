package ru.eshakin.weather_app.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.eshakin.weather_app.facade.SensorFacade;
import ru.eshakin.weather_app.models.dto.SensorDto;

/**
 * Api клиента для работы с сенсорами
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/sensors")
public class SensorController {

    private final SensorFacade sensorFacade;

    /**
     * Добавить сенсор
     * @param sensor добавляемый сенсор
     * @return HttpStatus.CREATED в случае успеха
     */
    @PostMapping("registration")
    public ResponseEntity<HttpStatus> create(@RequestBody @Validated SensorDto sensor) {
        sensorFacade.create(sensor);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    /**
     * Удалить сенсор
     * @param sensor удаляемый сенсор
     * @return HttpStatus.CREATED в случае успеха
     */
    @DeleteMapping
    public ResponseEntity<HttpStatus> delete(@RequestBody SensorDto sensor) {
        sensorFacade.delete(sensor);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
