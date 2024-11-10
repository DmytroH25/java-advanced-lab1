import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Gatherer;
import java.util.stream.Stream;

/**
 * Main class demonstrating the generation, filtering, and statistical analysis of Car objects.
 */
public class Main {
    public static void main(String[] args) {
        String skipBrand = "Toyota";
        int skipCount = 5;

        // Generates an infinite stream of Car objects, skipping the first N instances of a specific brand.
        Gatherer<Car, AtomicInteger, Car> gatherer = Gatherer.ofSequential(
                () -> new AtomicInteger(skipCount),
                (state, car, downstream) -> {
                    if (car.getBrand().equals(skipBrand) && state.get() > 0) {
                        state.decrementAndGet();
                        return true;
                    } else {
                        return downstream.push(car);
                    }
                },
                Gatherer.defaultFinisher()
        );

        List<Car> cars = Stream.generate(Car::randomCar)
                .gather(gatherer)
                .limit(500)
                .collect(Collectors.toList());

        // Filters cars by manufacturing date range and groups by car class.
        Map<String, List<Car>> carsByClass = cars.stream()
                .filter(car -> car.getMonthsSinceManufacture() <= 60)
                .collect(Collectors.groupingBy(Car::getCarClass));

        // Displays the filtered and grouped list of cars.
        carsByClass.forEach((carClass, carList) -> {
            System.out.println("Class: " + carClass + ", Cars: " + carList.size());
        });

        // Collects statistical data on car prices.
        Collector<Car, List<Integer>, Statistics> statisticsCollector = Collector.of(
                ArrayList::new,
                (list, car) -> list.add(car.getPriceUAH()),
                (list1, list2) -> {list1.addAll(list2);
                    return list1;},
                list -> {
                    int min = list.stream().min(Integer::compareTo).orElse(0);
                    int max = list.stream().max(Integer::compareTo).orElse(0);
                    double avg = list.stream().mapToInt(Integer::intValue).average().orElse(0);
                    double stdDev = Math.sqrt(list.stream().mapToDouble(price -> Math.pow(price - avg, 2)).sum() / list.size());
                    return new Statistics(min, max, avg, stdDev);
                }
        );

        Statistics stats = cars.stream().collect(statisticsCollector);
        System.out.println("Statistics: " + stats);

        // Calculates interquartile range and identifies outliers in car prices.
        List<Integer> sortedPrices = cars.stream()
                .map(Car::getPriceUAH)
                .sorted()
                .collect(Collectors.toList());

        int q1 = sortedPrices.get(sortedPrices.size() / 4);
        int q3 = sortedPrices.get(sortedPrices.size() * 3 / 4);
        int iqr = q3 - q1;

        int lowerBound = q1 - (int)(1.5 * iqr);
        int upperBound = q3 + (int)(1.5 * iqr);

        // Counts the number of inliers and outliers.
        long inliersCount = cars.stream()
                .filter(car -> car.getPriceUAH() >= lowerBound && car.getPriceUAH() <= upperBound)
                .count();

        long outliersCount = cars.stream()
                .filter(car -> car.getPriceUAH() < lowerBound || car.getPriceUAH() > upperBound)
                .count();

        // Creates a map with inliers and outliers counts.
        Map<String, Long> result = new HashMap<>();
        result.put("data", inliersCount);
        result.put("outliers", outliersCount);

        System.out.println(result);
    }
}
