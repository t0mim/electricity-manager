package fi.home.electricitymanager.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TariffTest {

    @Test
    public void fromString(){
        assertEquals(Tariff.DAY, Tariff.fromString("day"));
    }

}