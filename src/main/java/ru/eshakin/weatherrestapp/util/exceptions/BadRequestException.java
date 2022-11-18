package ru.eshakin.weatherrestapp.util.exceptions;

import lombok.Getter;
import ru.eshakin.weatherrestapp.util.ApiError;

@Getter
public class BadRequestException extends RuntimeException {

    private final ApiError apiError;

    public BadRequestException(String message) {
        super(message);
        this.apiError = new ApiError(message);
    }

}