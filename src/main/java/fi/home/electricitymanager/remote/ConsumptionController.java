package fi.home.electricitymanager.remote;

import fi.home.electricitymanager.model.ConsumptionDTO;
import fi.home.electricitymanager.model.Tariff;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumptionController {


    @RequestMapping(value = "/consumption/save", method = RequestMethod.POST, produces = "application/json")
    public ConsumptionDTO save(@RequestParam Long electricity) {
        return new ConsumptionDTO.Builder().electricityAmount(electricity)
                                    .month(1)
                                    .year(2016)
                                    .tariff(Tariff.DAY)
                                    .build();
    }
}
