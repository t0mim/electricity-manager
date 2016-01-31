package fi.home.electricitymanager.service;

import fi.home.electricitymanager.exception.ConsumptionNotFoundException;
import fi.home.electricitymanager.model.Consumption;
import fi.home.electricitymanager.model.ConsumptionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
                .month(consumption.getYearAndMonth().getMonthValue())
                .year(consumption.getYearAndMonth().getYear())
                .build();
    }

    @Override
    @Transactional
    public Long save(ConsumptionDTO consumptionDTO) {

        Consumption consumption = new Consumption();
        consumption.setElectricityAmount(consumptionDTO.getElectricityAmount());
        consumption.setTariff(consumptionDTO.getTariff());
        consumption.setYearAndMonth(consumptionDTO.getYearAndMonth());

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

}
