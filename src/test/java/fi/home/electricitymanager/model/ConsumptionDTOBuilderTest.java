package fi.home.electricitymanager.model;

import org.junit.Test;

import java.time.DateTimeException;
import java.time.Month;
import java.time.Year;

import static org.junit.Assert.assertEquals;

public class ConsumptionDTOBuilderTest {

    @Test
    public void testBuilderBuildsCorrectly ()
    {
        ConsumptionDTO consumptionDTO = new ConsumptionDTO.Builder()
                                            .tariff(Tariff.DAY)
                                            .electricityAmount(123L)
                                            .month(12)
                                            .year(2015)
                                            .build();


        assertEquals(Tariff.DAY, consumptionDTO.getTariff());
        assertEquals(new Long(123), consumptionDTO.getElectricityAmount());
        assertEquals(Month.DECEMBER, consumptionDTO.getMonth());
        assertEquals(Year.of(2015), consumptionDTO.getYear());
    }

    @Test (expected = ConsumptionDTO.Builder.ValidationException.class)
    public void builderThrowsError_whenAmountInvalid()
    {
        ConsumptionDTO consumptionDTO = new ConsumptionDTO.Builder()
                .tariff(Tariff.DAY)
                .electricityAmount(-123L)
                .month(12)
                .year(2015)
                .build();

    }

    @Test (expected = DateTimeException.class)
    public void builderThrowsError_whenMonthInvalid()
    {
        ConsumptionDTO consumptionDTO = new ConsumptionDTO.Builder()
                .tariff(Tariff.DAY)
                .electricityAmount(5l)
                .month(-1)
                .year(2015)
                .build();

    }
}