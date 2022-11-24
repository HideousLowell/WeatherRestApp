package ru.eshakin.weather_app.errors;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public
class ApiValidationError {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;
}
