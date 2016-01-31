package fi.home.electricitymanager.service;

import fi.home.electricitymanager.exception.ConsumptionNotFoundException;
import fi.home.electricitymanager.model.ConsumptionDTO;

public interface ConsumptionService {

    public ConsumptionDTO get(Long id) throws ConsumptionNotFoundException;
    public Long save(ConsumptionDTO consumption);
    public void delete(Long id) throws ConsumptionNotFoundException;

}
