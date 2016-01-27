package fi.home.electricitymanager.service;

import fi.home.electricitymanager.model.Consumption;
import fi.home.electricitymanager.model.ConsumptionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional(readOnly = true)
public class ConsumptionServiceImpl implements ConsumptionService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    ConsumptionRepository repository;

    @Override
    public ConsumptionDTO get(Long id) {
        Consumption consumption = repository.getOne(id);
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
        return repository.save(consumption).getId();
    }

}
