package ru.eshakin.WeatherRestApp.util.exceptions;

import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.eshakin.WeatherRestApp.util.ApiError;
import ru.eshakin.WeatherRestApp.util.ApiValidationError;

import java.util.List;
import java.util.stream.Collectors;

@Component
@ControllerAdvice
public class RestExceptionsHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleExceptionInternal(@NonNull Exception ex,
                                                             Object body,
                                                             @NonNull HttpHeaders headers,
                                                             @NonNull HttpStatus status,
                                                             @NonNull WebRequest request) {

        if (ex.getClass().equals(MethodArgumentNotValidException.class)) {
            MethodArgumentNotValidException e = (MethodArgumentNotValidException) ex;
            ApiError apiError = new ApiError("Validation failed");

            List<ApiValidationError> validationErrors = e
                    .getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(fieldError -> new ApiValidationError(fieldError.getObjectName(), fieldError.getField(),
                            fieldError.getRejectedValue(),
                            fieldError.getDefaultMessage()))
                    .collect(Collectors.toList());

            apiError.setSubErrors(validationErrors);
            return new ResponseEntity<>(apiError, status);
        }

        return new ResponseEntity<>(new ApiError("Error"), status);
    }

    @ExceptionHandler
    private ResponseEntity<ApiError> handleException(RuntimeException exception) {
        ApiError response = new ApiError(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
