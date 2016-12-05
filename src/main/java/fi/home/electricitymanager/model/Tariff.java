package fi.home.electricitymanager.model;

public enum Tariff {
    DAY("DAY"), NIGHT("NIGHT");

    private String value;

    private Tariff(String value){
        this.value = value;
    }

    public static Tariff fromString(String value) {
        return Tariff.valueOf(Tariff.class, value.toUpperCase());
    }
}
