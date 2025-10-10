package lessons.lesson06.db;

import lessons.lesson06.utils.BookingStatus;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BookingRepository {
    private final Map<Integer, Map<String, Booking>> data;

    public BookingRepository(Map<Integer, Map<String, Booking>> data) {
        this.data = data;
    }

    public List<Booking> findOutdatedBookings(final Integer flightId) {
        LocalDateTime deadline = LocalDateTime.now().minusMinutes(24);

        return data.getOrDefault(flightId, new HashMap<>()).values().stream().filter(
            b -> (b.getStatus().equals(BookingStatus.BOOKED) && b.getBookedAt().isBefore(deadline))).toList();
    }


    public boolean existsByFlightIdAndCode(final Integer flightId, final String code) {
        return data.containsKey(flightId) && data.get(flightId).containsKey(code);
    }

    public Optional<Booking> findByFlightIdAndCode(final Integer flightId, final String code) {
        return Optional.ofNullable(data.getOrDefault(flightId, new HashMap<>()).get(code));
    }

    public void save(final Booking booking) throws IOException {
        String code = booking.getCode();

        if (!data.containsKey(booking.getFlightId())) {
            Map<String, Booking> bookingByFlight = new HashMap<>();
            bookingByFlight.put(code, booking);
            data.put(booking.getFlightId(), bookingByFlight);
        } else {
            data.get(booking.getFlightId()).put(code, booking);
        }

        BookingDbManager.storeBookingInfo(booking);
    }

    public void updateStatus(final Booking booking) throws IOException {
        data.get(booking.getFlightId()).get(booking.getCode()).setStatus(booking.getStatus());

        BookingDbManager.updateBookingInfo(booking);
    }

    public void bulkDelete(final List<Booking> bookings) throws IOException, Exception {
        for (Booking booking : bookings) {
            data.get(booking.getFlightId()).remove(booking.getCode());
        }

        BookingDbManager.bulkDeleteBookingInfo(bookings);
    }
}
