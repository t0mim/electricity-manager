package fi.home.electricitymanager.service;

import fi.home.electricitymanager.ElectricitymanagerApplication;
import fi.home.electricitymanager.model.ConsumptionDTO;
import fi.home.electricitymanager.model.Tariff;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ElectricitymanagerApplication.class)
@ActiveProfiles("test")
public class ConsumptionServiceImplTest {

    @Autowired
    ConsumptionService consumptionService;

    @Test
    public void testSave() {
        ConsumptionDTO dto = new ConsumptionDTO.Builder()
                .tariff(Tariff.DAY)
                .electricityAmount(new Long(1500))
                .month(11)
                .year(2015)
                .build();

        consumptionService.save(dto);
        ConsumptionDTO actual = consumptionService.get(new Long(1));
        Assert.assertEquals(actual.getElectricityAmount(), new Long(1500));
        Assert.assertNotNull(consumptionService.get(new Long(1)));
    }

}