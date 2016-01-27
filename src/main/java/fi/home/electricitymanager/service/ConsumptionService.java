package fi.home.electricitymanager.service;

import fi.home.electricitymanager.model.ConsumptionDTO;

public interface ConsumptionService {

    public ConsumptionDTO get(Long id);
    public Long save(ConsumptionDTO consumption);
    //public void delete(Long id);

}
