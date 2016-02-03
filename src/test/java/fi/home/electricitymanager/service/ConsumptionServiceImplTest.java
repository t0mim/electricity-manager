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

import java.time.Month;
import java.time.Year;
import java.util.List;

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

        assertEquals(new Long(1500), actual.getElectricityAmount());
        assertEquals(Tariff.DAY, actual.getTariff());
        assertEquals(Year.of(2015), actual.getYear());
        assertEquals(Month.NOVEMBER, actual.getMonth());

    }

    @Test(expected = Test.None.class)
    public void testUpdate() throws ConsumptionNotFoundException {

        final Long newElectricityAmount = new Long(3000L);
        final Month newMonth = Month.FEBRUARY;

        Long id = consumptionService.save(generateConsumption(1,2016));

        ConsumptionDTO old = consumptionService.get(id);



        ConsumptionDTO updated = new ConsumptionDTO.Builder(old.getId())
                .tariff(old.getTariff())
                .electricityAmount(newElectricityAmount) // updated
                .month(newMonth.getValue()) // updated
                .year(old.getYear().getValue())
                .build();

        Long idAfterUpdate = consumptionService.save(updated);

        assertEquals(id, idAfterUpdate);

        assertEquals(newElectricityAmount, updated.getElectricityAmount());
        assertEquals(newMonth, updated.getMonth());

    }

    @Test( expected = ConsumptionNotFoundException.class)
    public void testDelete() throws ConsumptionNotFoundException {

        Long id = consumptionService.save(testDto);

        consumptionService.delete(id);

        consumptionService.get(id);

    }

    @Test
    public void testFindByYear() {

        consumptionService.save(generateConsumption(10,2015));
        consumptionService.save(generateConsumption(11,2015));
        consumptionService.save(generateConsumption(12,2015));
        consumptionService.save(generateConsumption(1,2016));

        List<ConsumptionDTO> consumptionsFrom2015 = consumptionService.findByYear(Year.of(2015));
        List<ConsumptionDTO> consumptionsFrom2016 = consumptionService.findByYear(Year.of(2016));

        assertEquals(3, consumptionsFrom2015.size());
        assertEquals(1, consumptionsFrom2016.size());
    }

    public ConsumptionDTO generateConsumption(int month, int year) {
        return new ConsumptionDTO.Builder()
                .tariff(Tariff.DAY)
                .electricityAmount(new Long(1500))
                .month(month)
                .year(year)
                .build();
    }

}