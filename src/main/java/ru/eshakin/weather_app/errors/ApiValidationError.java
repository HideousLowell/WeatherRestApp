package ru.eshakin.weather_app.errors;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * POJO класс для ошибок валидации
 */
@Data
@AllArgsConstructor
public
class ApiValidationError {
    /**
     * Имя объекта, в котором обнаружена ошибка
     */
    private String object;

    /**
     * Поле, в котором обнаружена ошибка
     */
    private String field;

    /**
     * Значение, в котором обнаружена ошибка
     */
    private Object rejectedValue;

    /**
     * Сообщенеи об ошибке
     */
    private String message;
}
