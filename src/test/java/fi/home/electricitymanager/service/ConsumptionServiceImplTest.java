package fi.home.electricitymanager.service;

import fi.home.electricitymanager.ElectricitymanagerApplication;
import fi.home.electricitymanager.exception.ConsumptionNotFoundException;
import fi.home.electricitymanager.model.ConsumptionDTO;
import fi.home.electricitymanager.model.Tariff;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.YearMonth;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ElectricitymanagerApplication.class)
@ActiveProfiles("test")
public class ConsumptionServiceImplTest {

    @Autowired
    ConsumptionService consumptionService;

    private ConsumptionDTO testDto = new ConsumptionDTO.Builder()
            .tariff(Tariff.DAY)
            .electricityAmount(new Long(1500))
            .month(11)
            .year(2015)
            .build();

    @Test(expected = ConsumptionNotFoundException.class)
    public void testGet_shouldThrowException() throws ConsumptionNotFoundException {

        final Long wrongId = new Long(9999);

        consumptionService.get(wrongId);
    }

    @Test(expected = Test.None.class)
    public void testSaveAndGet() throws ConsumptionNotFoundException {


        Long consumptionId = consumptionService.save(testDto);

        ConsumptionDTO actual = consumptionService.get(consumptionId);

        final YearMonth november2015 = YearMonth.of(2015, 11);

        assertEquals(new Long(1500), actual.getElectricityAmount());
        assertEquals(Tariff.DAY, actual.getTariff());
        assertEquals(november2015, actual.getYearAndMonth());

    }

    @Test( expected = ConsumptionNotFoundException.class)
    public void testDelete() throws ConsumptionNotFoundException {

        Long id = consumptionService.save(testDto);

        consumptionService.delete(id);

        consumptionService.get(id);

    }

}