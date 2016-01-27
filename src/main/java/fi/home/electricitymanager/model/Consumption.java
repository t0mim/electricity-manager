package fi.home.electricitymanager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.YearMonth;

@Entity
public class Consumption {

    @Id
    @GeneratedValue (strategy= GenerationType.AUTO)
    private Long id;
    private Long electricityAmount;
    private Tariff tariff;
    private YearMonth yearAndMonth;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getElectricityAmount() {
        return electricityAmount;
    }

    public void setElectricityAmount(Long electricityAmount) {
        this.electricityAmount = electricityAmount;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public YearMonth getYearAndMonth() {
        return yearAndMonth;
    }

    public void setYearAndMonth(YearMonth yearAndMonth) {
        this.yearAndMonth = yearAndMonth;
    }
}
