package fi.home.electricitymanager.model;

import java.time.YearMonth;

public class ConsumptionDTO {

    private final Long id;
    private final Long electricityAmount;
    private final Tariff tariff;
    private final YearMonth yearAndMonth;

    private ConsumptionDTO(Builder builder) {
        this.id = builder.id;
        this.electricityAmount = builder.electricityAmount;
        this.tariff = builder.tariff;
        this.yearAndMonth = builder.yearAndMonth;

    }

    public Long getId() {
        return id;
    }

    public Long getElectricityAmount() {
        return electricityAmount;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public YearMonth getYearAndMonth() {
        return yearAndMonth;
    }


    public static class Builder {

        private Long id;
        private Long electricityAmount;
        private Tariff tariff;
        private YearMonth yearAndMonth;

        public Builder() {

            yearAndMonth = YearMonth.now();
        }

        public Builder(Long id) {
            this.id = id;
            yearAndMonth = YearMonth.now();
        }

        public Builder electricityAmount(Long electricityAmount) {
            this.electricityAmount = electricityAmount;
            return this;
        }

        public Builder tariff(Tariff tariff) {
            this.tariff = tariff;
            return this;
        }

        public Builder month(int month) {
            this.yearAndMonth = this.yearAndMonth.withMonth(month);
            return this;
        }

        public Builder year(int year) {
            this.yearAndMonth = this.yearAndMonth.withYear(year);
            return this;
        }


        public ConsumptionDTO build() {
            return new ConsumptionDTO(this);
        }

    }

}
