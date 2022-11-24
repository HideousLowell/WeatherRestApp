package ru.eshakin.weather_app.exceptions;

import lombok.Getter;
import ru.eshakin.weather_app.errors.ApiError;

@Getter
public class BadRequestException extends RuntimeException {

    private final ApiError apiError;

    public BadRequestException(String message) {
        super(message);
        this.apiError = new ApiError(message);
    }

}