package fi.home.electricitymanager.service;

import fi.home.electricitymanager.model.Consumption;
import fi.home.electricitymanager.model.ConsumptionDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Year;
import java.util.List;

public interface ConsumptionRepository extends JpaRepository<Consumption, Long> {

    public List<ConsumptionDTO> findByYear(Year year);
}
