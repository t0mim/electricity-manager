package fi.home.electricitymanager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Month;
import java.time.Year;

@Entity
public class Consumption {

    @Id
    @GeneratedValue (strategy= GenerationType.AUTO)
    private Long id;
    private Long electricityAmount;
    private Tariff tariff;
    private Year year;
    private Month month;

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

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public Month getMonth() { return month; }

    public void setMonth(Month month) { this.month = month; }
}
