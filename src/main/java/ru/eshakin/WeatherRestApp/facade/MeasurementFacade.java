package ru.eshakin.WeatherRestApp.facade;

import ru.eshakin.WeatherRestApp.models.dto.MeasurementDto;
import ru.eshakin.WeatherRestApp.models.entity.Measurement;

import java.util.List;

public interface MeasurementFacade {

    public List<MeasurementDto> findAll();
    public void create(MeasurementDto measurement);
    public int getRainyDaysCount();
    public MeasurementDto convertToDto(Measurement measurement);

    public Measurement convertToEntity(MeasurementDto measurementDTO);

}
