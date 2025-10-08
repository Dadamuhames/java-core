package lessons.lesson06;

import lessons.lesson06.db.Booking;
import lessons.lesson06.db.BookingRepository;
import lessons.lesson06.service.PlaneDisplayService;
import lessons.lesson06.service.UserInputService;
import lessons.lesson06.utils.PlaceClassEnum;

import java.io.IOException;

public class BookingSystem {
    private final UserInputService userInputService;
    private final BookingRepository bookingRepository;
    private final PlaneDisplayService planeDisplayService;

    private final int MAX_ROWS = 15;

    public BookingSystem(UserInputService userInputService, BookingRepository bookingRepository, PlaneDisplayService planeDisplayService) {
        this.planeDisplayService = planeDisplayService;
        this.userInputService = userInputService;
        this.bookingRepository = bookingRepository;
    }


    public void getBookingFlight() {
        String code = userInputService.promptSeatCode();

        Booking booking = bookingRepository.findByCode(code).orElse(null);

        if (booking == null) {
            System.out.println("Seat is free to book");
            return;
        }

        System.out.println(booking);
    }

    public boolean isBusinessClass(final String code) {
        int col = Integer.parseInt(code.substring(1));
        return (col >= 1 && col <= 5);
    }


    public void bookASeat() {
        String code = userInputService.promptSeatCodeToBook();

        String fio = userInputService.promptFio();

        boolean isBusinessClass = isBusinessClass(code);

        PlaceClassEnum placeClassEnum = isBusinessClass ? PlaceClassEnum.BUSINESS : PlaceClassEnum.BROKE;

        Booking booking = new Booking(code, fio, placeClassEnum);

        try {
            bookingRepository.save(booking);
            System.out.println("Place successfully booked!");
        } catch (IOException e) {
            System.out.printf("Error - %s\n", e.getMessage());
        }
    }


    public void printPlane() {
        String[] cols = {"F", "E", "D", "C", "B", "A"};

        for (String col : cols) {
            if(col.equals("E") || col.equals("B")) {
                System.out.print(" ".repeat(35));
                System.out.print("|| ");
            }

            for (int i = 1; i <= MAX_ROWS; i++) {
                if (i < 6 && (col.equals("E") || col.equals("B"))) {
                    continue;
                }

                String code = String.format("%s%d", col, i);
                boolean isSeatBooked = bookingRepository.existsByCode(code);
                String seatDisplay = planeDisplayService.getSeatDisplay(code, isSeatBooked);
                System.out.print(seatDisplay);

                if (i == 5) {
                    System.out.print("|| ");
                }
            }

            if (col.equals("D")) {
                System.out.print("\n\n");
            }

            System.out.print("\n");
        }
    }
}
