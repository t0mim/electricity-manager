package fi.home.electricitymanager.model;

import javax.persistence.Entity;
import java.time.YearMonth;

/**
 * Created by tomim on 27/12/15.
 */

@Entity
public class Consumption {

    private final long electricityAmount;
    private final Tariff tariff;
    private final YearMonth yearAndMonth;

    private Consumption (ConsumptionBuilder builder) {
        this.electricityAmount = builder.electricityAmount;
        this.tariff = builder.tariff;
        this.yearAndMonth = builder.yearAndMonth;

    }

    public long getElectricityAmount() {
        return electricityAmount;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public YearMonth getYearAndMonth() {
        return yearAndMonth;
    }


    public static class ConsumptionBuilder {

        private long electricityAmount;
        private Tariff tariff;
        private YearMonth yearAndMonth;

        public ConsumptionBuilder() {

            yearAndMonth = YearMonth.now();
        }

        public ConsumptionBuilder electricityAmount(long electricityAmount) {
            this.electricityAmount = electricityAmount;
            return this;
        }

        public ConsumptionBuilder tariff(Tariff tariff) {
            this.tariff = tariff;
            return this;
        }

        public ConsumptionBuilder month(int month) {
            this.yearAndMonth.withMonth(month);
            return this;
        }

        public ConsumptionBuilder year(int year) {
            this.yearAndMonth.withYear(year);
            return this;
        }


        public Consumption build() {
            return new Consumption(this);
        }

    }

}
