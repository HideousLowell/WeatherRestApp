package ru.eshakin.weatherrestapp.util;

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
