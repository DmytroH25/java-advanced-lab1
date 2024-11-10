import java.util.concurrent.ThreadLocalRandom;

/**
 * Represents a car with specific attributes: brand, months since manufacture, class, and price in UAH.
 */
public class Car {
    private final String brand;
    private final int monthsSinceManufacture;
    private final String carClass;
    private final int priceUAH;

    /**
     * Constructor to initialize a Car object with specified attributes.
     *
     * @param brand                the brand of the car
     * @param monthsSinceManufacture the number of months since the car was manufactured
     * @param carClass             the class/category of the car
     * @param priceUAH             the price of the car in UAH
     */
    public Car(String brand, int monthsSinceManufacture, String carClass, int priceUAH) {
        this.brand = brand;
        this.monthsSinceManufacture = monthsSinceManufacture;
        this.carClass = carClass;
        this.priceUAH = priceUAH;
    }

    public String getBrand() { return brand; }
    public int getMonthsSinceManufacture() { return monthsSinceManufacture; }
    public String getCarClass() { return carClass; }
    public int getPriceUAH() { return priceUAH; }

    /**
     * Generates a Car object with random attributes.
     *
     * @return a Car object with randomly generated attributes
     */
    public static Car randomCar() {
        String[] brands = {"Toyota", "Honda", "BMW", "Ford", "Audi"};
        String[] classes = {"Economy", "SUV", "Luxury", "Sedan"};
        String brand = brands[ThreadLocalRandom.current().nextInt(brands.length)];
        int monthsSinceManufacture = ThreadLocalRandom.current().nextInt(1, 120);
        String carClass = classes[ThreadLocalRandom.current().nextInt(classes.length)];
        int priceUAH = ThreadLocalRandom.current().nextInt(200000, 1000000);
        return new Car(brand, monthsSinceManufacture, carClass, priceUAH);
    }

    @Override
    public String toString() {
        return String.format("Car{brand='%s', monthsSinceManufacture=%d, carClass='%s', priceUAH=%d}",
                brand, monthsSinceManufacture, carClass, priceUAH);
    }
}
