package fi.home.electricitymanager.service;

import fi.home.electricitymanager.exception.ConsumptionNotFoundException;
import fi.home.electricitymanager.model.Consumption;
import fi.home.electricitymanager.model.ConsumptionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.Collections;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class ConsumptionServiceImpl implements ConsumptionService {

    @Autowired
    ConsumptionRepository repository;

    @Override
    public ConsumptionDTO get(Long id) throws ConsumptionNotFoundException {

        Consumption consumption = repository.findOne(id);

        if (consumption == null) throw new ConsumptionNotFoundException("Consumption not found id = " + id);

        return new ConsumptionDTO.Builder(consumption.getId())
                .tariff(consumption.getTariff())
                .electricityAmount(consumption.getElectricityAmount())
                .month(consumption.getMonth().getValue())
                .year(consumption.getYear().getValue())
                .build();
    }

    @Override
    @Transactional
    public Long save(ConsumptionDTO consumptionDTO) {

        Consumption consumption = new Consumption();

        if (!consumptionDTO.isNew())
            consumption.setId(consumptionDTO.getId());

        consumption.setElectricityAmount(consumptionDTO.getElectricityAmount());
        consumption.setTariff(consumptionDTO.getTariff());
        consumption.setYear(consumptionDTO.getYear());
        consumption.setMonth(consumptionDTO.getMonth());

        return repository.saveAndFlush(consumption).getId();
    }

    @Override
    @Transactional
    public void delete(Long id) throws ConsumptionNotFoundException {

        try {
            repository.delete(id);
        } catch (Exception e) {
            throw new ConsumptionNotFoundException(e.getMessage());
        }
    }

    @Override
    public List<ConsumptionDTO> findByYear(Year year) {
        if (year != null)
            return repository.findByYear(year);
        return Collections.emptyList();
    }


}
