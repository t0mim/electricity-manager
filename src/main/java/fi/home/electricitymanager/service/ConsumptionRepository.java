package fi.home.electricitymanager.service;

import fi.home.electricitymanager.model.Consumption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumptionRepository extends JpaRepository<Consumption, Long> {
}
