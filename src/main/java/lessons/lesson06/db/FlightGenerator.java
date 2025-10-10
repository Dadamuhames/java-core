package lessons.lesson06.db;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class FlightGenerator {
    public static Map<Integer, Flight> getFlights() {
        Map<Integer, Flight> flightMap = new HashMap<>();

        LocalDate today = LocalDate.now().plusDays(2);

        LocalDateTime flightOneDate = LocalDateTime.of(today, LocalTime.of(19, 0));
        LocalDateTime flightTwoDate = LocalDateTime.of(today, LocalTime.of(23, 0));

        flightMap.put(1, new Flight(1, "Tashkent", "Alabama", 200.0, flightOneDate));
        flightMap.put(2, new Flight(2, "Tashkent", "Moskov", 300.0, flightTwoDate));

        return flightMap;
    }
}
