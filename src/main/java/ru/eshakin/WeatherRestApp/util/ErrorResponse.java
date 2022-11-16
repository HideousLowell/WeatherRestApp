package ru.eshakin.WeatherRestApp.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {
    private String message;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date timestamp;
}
