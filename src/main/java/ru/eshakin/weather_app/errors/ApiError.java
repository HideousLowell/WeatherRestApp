package ru.eshakin.weather_app.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Класс для упаковки возврата на клиент возникающих ошибок
 * Генерирует дату создания
 */
@Getter
@Setter
public class ApiError implements Serializable {

    /**
     * Основное сообщение ошибки
     */
    private final String message;

    /**
     * Список дополнительных ошибок
     */
    private List<ApiValidationError> subErrors;

    /**
     * Дата и время ошибки
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date timestamp = new Date();

    public ApiError(String message) {
        super();
        this.message = message;
    }
}
