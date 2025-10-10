package lessons.lesson06.db;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Flight(Integer id, String flightFrom, String flightTo, Double price, LocalDateTime flightAt) {
    @Override
    public String toString() {
        return String.format("Id: %d\nFrom: %s\nTo: %s\nAt: %s\nPrice: %s", id, flightFrom, flightTo, flightAt, price);
    }
}
