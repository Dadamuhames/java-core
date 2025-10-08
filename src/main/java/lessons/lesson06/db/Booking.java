package lessons.lesson06.db;

import lessons.lesson06.utils.PlaceClassEnum;

public record Booking(String code, String fio, PlaceClassEnum placeClass) {

    @Override
    public String toString() {
        return String.format("Seat code: %s\nFio: %s\nClass: %s", code, fio, placeClass);
    }
}
