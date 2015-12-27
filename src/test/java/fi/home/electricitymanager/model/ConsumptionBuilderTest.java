package fi.home.electricitymanager.model;

import org.junit.Test;

import java.sql.Time;
import java.time.Year;

import static org.junit.Assert.*;

/**
 * Created by tomim on 27/12/15.
 */
public class ConsumptionBuilderTest {

    @Test
    public void testBuilderBuildsCorrectly ()
    {
        Consumption.ConsumptionBuilder builder = new Consumption.ConsumptionBuilder();
        Consumption consumption = builder.tariff(Tariff.DAY).electricityAmount(123).month(12).year(2015).build();


        assertEquals(Tariff.DAY, consumption.getTariff());
        assertEquals(123, consumption.getElectricityAmount());
        assertEquals(12, consumption.getYearAndMonth().getMonthValue());
        assertEquals(2015, consumption.getYearAndMonth().getYear());
    }
}