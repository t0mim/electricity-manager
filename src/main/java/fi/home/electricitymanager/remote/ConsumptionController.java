package fi.home.electricitymanager.remote;

import fi.home.electricitymanager.model.ConsumptionDTO;
import fi.home.electricitymanager.model.Tariff;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumptionController {


    @RequestMapping(value = "/consumption/save", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<ConsumptionDTO> save(@RequestParam(name = "amount") Long electricity,
                               @RequestParam(name = "month") Integer month,
                               @RequestParam(name = "year") Integer year,
                               @RequestParam(name = "tariff") String tariff) {
        try {
            ConsumptionDTO dto = new ConsumptionDTO.Builder().electricityAmount(electricity)
                    .month(month)
                    .year(year)
                    .tariff(Tariff.fromString(tariff))
                    .build();
            return ResponseEntity.ok(dto);
        } catch (ConsumptionDTO.Builder.ValidationException validationException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
