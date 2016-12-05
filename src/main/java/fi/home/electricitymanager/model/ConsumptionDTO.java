package fi.home.electricitymanager.model;

import java.time.Month;
import java.time.Year;

public class ConsumptionDTO {

    private final Long id;
    private final Long electricityAmount;
    private final Tariff tariff;
    private final Year year;
    private final Month month;

    private ConsumptionDTO(Builder builder) {
        this.id = builder.id;
        this.electricityAmount = builder.electricityAmount;
        this.tariff = builder.tariff;
        this.year = builder.year;
        this.month = builder.month;

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

    public Year getYear() {
        return year;
    }

    public Month getMonth() {
        return month;
    }

    public Boolean isNew() { return id == null; }

    public static class Builder {

        private Long id;
        private Long electricityAmount;
        private Tariff tariff;
        private Year year;
        private Month month;

        public Builder() {

            year = Year.now();
        }

        public Builder(Long id) {
            this.id = id;
            year = Year.now();
        }

        public Builder electricityAmount(Long electricityAmount) {
            this.electricityAmount = electricityAmount;
            return this;
        }

        public Builder tariff(Tariff tariff) {
            this.tariff = tariff;
            return this;
        }

        public Builder year(int year) {
            this.year = Year.of(year);
            return this;
        }

        public Builder month(int month) {
            this.month = Month.of(month);
            return this;
        }


        public ConsumptionDTO build() {

            if(electricityAmount <= 0) {
                throw new ValidationException("Electricity amount should be greater than zero");
            }

            return new ConsumptionDTO(this);
        }

        public class ValidationException extends RuntimeException {
            public ValidationException(String message) { super(message); }
        }
    }

}
