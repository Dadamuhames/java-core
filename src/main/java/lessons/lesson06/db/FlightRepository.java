package lessons.lesson06.db;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FlightRepository {
    private final Map<Integer, Flight> data;

    public FlightRepository(Map<Integer, Flight> data) {this.data = data;}

    public List<Flight> findByFlightAtBefore(final LocalDateTime dateTime) {
        return this.data.values().stream().filter(f -> f.flightAt().isAfter(dateTime)).toList();
    }

    public Optional<Flight> findById(final Integer id) {
        return Optional.ofNullable(data.get(id));
    }
}
