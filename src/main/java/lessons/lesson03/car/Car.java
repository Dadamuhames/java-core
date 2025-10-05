package lessons.lesson03.car;

public class Car {
    private final String country;
    private final String model;
    private final Integer yearOfManufacture;

    public Car(String country, String model, Integer yearOfManufacture) {
        this.country = country;
        this.model = model;
        this.yearOfManufacture = yearOfManufacture;
    }

    public void printCarInfo() {
        String cardInfoString = String.format("Country: %s\nModel: %s\nYear of Manufacture: %d", country, model, yearOfManufacture);

        System.out.println(cardInfoString);
    }
}
