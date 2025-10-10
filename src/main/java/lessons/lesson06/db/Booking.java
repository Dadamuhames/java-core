package lessons.lesson06.db;

import lessons.lesson06.utils.BookingStatus;
import lessons.lesson06.utils.PlaceClassEnum;

import java.time.LocalDateTime;

public class Booking {
    private final String code;
    private final String fio;
    private final PlaceClassEnum placeClass;
    private final Integer flightId;
    private final LocalDateTime bookedAt;
    private BookingStatus status;

    public Booking(String code, String fio, PlaceClassEnum placeClass, Integer flightId, LocalDateTime bookedAt, BookingStatus status) {
        this.code = code;
        this.fio = fio;
        this.placeClass = placeClass;
        this.flightId = flightId;
        this.bookedAt = bookedAt;
        this.status = status;
    }

    public Booking(String code, String fio, PlaceClassEnum placeClass, Integer flightId, LocalDateTime bookedAt) {
        this.code = code;
        this.fio = fio;
        this.placeClass = placeClass;
        this.flightId = flightId;
        this.bookedAt = bookedAt;
        this.status = BookingStatus.BOOKED;
    }

    public LocalDateTime getBookedAt() {
        return bookedAt;
    }

    public String getCode() {
        return code;
    }

    public Integer getFlightId() {
        return this.flightId;
    }

    public BookingStatus getStatus() {
        return this.status;
    }

    public void setStatus(final BookingStatus status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return String.format("Seat code: %s\nFio: %s\nClass: %s\nBooked at: %s\nStatus: %s", code, fio, placeClass, bookedAt, status);
    }

    public String toDbString() {
        return String.format("%s;%s;%s;%s;%s;%s", code, fio, placeClass, flightId, bookedAt, status);
    }
}