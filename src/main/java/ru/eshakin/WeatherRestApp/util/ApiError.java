package ru.eshakin.WeatherRestApp.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ApiError {

    private final String message;

    private List<ApiValidationError> subErrors;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date timestamp = new Date();

    public ApiError(String message) {
        super();
        this.message = message;
    }
}
