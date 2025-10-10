package lessons.lesson06.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import lessons.lesson06.db.Booking;
import lessons.lesson06.db.BookingRepository;
import lessons.lesson06.db.Flight;
import lessons.lesson06.utils.ApplicationStateHolder;
import lessons.lesson06.utils.BookingStatus;
import lessons.lesson06.utils.PlaceClassEnum;

public class BookingService {
    private final UserInputService userInputService;
    private final BookingRepository bookingRepository;
    private final PlaneDisplayService planeDisplayService;
    private final ApplicationStateHolder applicationStateHolder;

    private final int MAX_ROWS = 15;
    private final String[] cols = {"F", "E", "D", "C", "B", "A"};


    public BookingService(UserInputService userInputService, BookingRepository bookingRepository, PlaneDisplayService planeDisplayService, ApplicationStateHolder applicationStateHolder) {
        this.planeDisplayService = planeDisplayService;
        this.userInputService = userInputService;
        this.bookingRepository = bookingRepository;
        this.applicationStateHolder = applicationStateHolder;
    }


    public Booking getBooking() throws Exception {
        Flight flight = applicationStateHolder.getCurrentFlight();

        assert flight != null;

        String code = userInputService.promptSeatCode();

        return bookingRepository.findByFlightIdAndCode(flight.id(), code).orElseThrow(() -> new Exception("Seat is not booked"));
    }


    public String getBookingInfo() {
        String result;

        try {
            result = getBooking().toString();
        } catch (Exception e) {
            result = e.getMessage();
        }

        return result;
    }

    public boolean isBusinessClass(final String code) {
        int col = Integer.parseInt(code.substring(1));
        return (col >= 1 && col <= 5);
    }

    public void bookASeat() {
        Flight currentFlight = applicationStateHolder.getCurrentFlight();

        String code = userInputService.promptSeatCodeToBook(currentFlight.id());

        String fio = userInputService.promptFio();

        boolean isBusinessClass = isBusinessClass(code);

        PlaceClassEnum placeClassEnum = isBusinessClass ? PlaceClassEnum.BUSINESS : PlaceClassEnum.ECONOMY;

        Booking booking = new Booking(code, fio, placeClassEnum, currentFlight.id(), LocalDateTime.now());

        try {
            bookingRepository.save(booking);
            System.out.println("Place successfully booked!");
        } catch (IOException e) {
            System.out.printf("Error - %s\n", e.getMessage());
        }
    }

    public void printPlane() {
        Flight flight = applicationStateHolder.getCurrentFlight();

        for (String col : cols) {
            if (col.equals("E") || col.equals("B")) {
                System.out.print(" ".repeat(35));
                System.out.print("|| ");
            }

            for (int i = 1; i <= MAX_ROWS; i++) {
                if (i < 6 && (col.equals("E") || col.equals("B"))) {
                    continue;
                }

                String code = String.format("%s%d", col, i);
                boolean isSeatBooked = bookingRepository.existsByFlightIdAndCode(flight.id(), code);
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

        System.out.println("\n=================");

        System.out.println(flight.toString());

        System.out.println("=================\n");
    }

    public void clearUnpaidBookings() throws Exception {
        Flight flight = applicationStateHolder.getCurrentFlight();

        List<Booking> outdatedBookings = bookingRepository.findOutdatedBookings(flight.id());

        bookingRepository.bulkDelete(outdatedBookings);
    }

    public void changeBookingStatus() {
        System.out.println("Changing booking status to paid");

        try {
            Booking booking = getBooking();
            booking.setStatus(BookingStatus.PAID);
            bookingRepository.updateStatus(booking);
        } catch (Exception e) {
            System.out.println("Seat is not booked");
        }
    }
}
