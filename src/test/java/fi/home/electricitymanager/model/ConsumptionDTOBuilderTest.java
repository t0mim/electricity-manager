package fi.home.electricitymanager.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tomim on 27/12/15.
 */
public class ConsumptionDTOBuilderTest {

    @Test
    public void testBuilderBuildsCorrectly ()
    {
        ConsumptionDTO.ConsumptionBuilder builder = new ConsumptionDTO.ConsumptionBuilder();
        ConsumptionDTO consumptionDTO = builder.tariff(Tariff.DAY).electricityAmount(123).month(12).year(2015).build();


        assertEquals(Tariff.DAY, consumptionDTO.getTariff());
        assertEquals(123, consumptionDTO.getElectricityAmount());
        assertEquals(12, consumptionDTO.getYearAndMonth().getMonthValue());
        assertEquals(2015, consumptionDTO.getYearAndMonth().getYear());
    }
}