package fi.home.electricitymanager.service;

import fi.home.electricitymanager.exception.ConsumptionNotFoundException;
import fi.home.electricitymanager.model.ConsumptionDTO;

import java.time.Year;
import java.util.List;

public interface ConsumptionService {

    public ConsumptionDTO get(Long id) throws ConsumptionNotFoundException;
    public Long save(ConsumptionDTO consumption);
    public void delete(Long id) throws ConsumptionNotFoundException;
    public List<ConsumptionDTO> findByYear(Year year);

}
