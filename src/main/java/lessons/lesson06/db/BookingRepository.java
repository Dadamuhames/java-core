package lessons.lesson06.db;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BookingRepository {
    private final Map<String, Booking> data;

    public BookingRepository(Map<String, Booking> data) {
        this.data = data;
    }


    public List<Booking> findAll() {
        return data.values().stream().toList();
    }


    public boolean existsByCode(final String code) {
        return data.containsKey(code);
    }


    public Optional<Booking> findByCode(final String code) {
        return Optional.ofNullable(data.get(code));
    }


    public void save(final Booking booking) throws IOException {
        String code = booking.code();

        data.put(code, booking);

        BookingDbManager.storeBookingInfo(booking);
    }

}
