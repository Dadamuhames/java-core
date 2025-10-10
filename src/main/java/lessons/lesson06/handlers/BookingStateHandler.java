package lessons.lesson06.handlers;

import lessons.lesson06.service.BookingService;
import lessons.lesson06.service.KeyboardService;
import lessons.lesson06.utils.ApplicationState;
import lessons.lesson06.utils.ApplicationStateHolder;
import lessons.lesson06.utils.Utils;

import java.util.Scanner;

public class BookingStateHandler {
    private final Scanner scanner;
    private final BookingService bookingSystem;
    private final ApplicationStateHolder stateHolder;

    public BookingStateHandler(Scanner scanner, BookingService bookingSystem, ApplicationStateHolder stateHolder) {
        this.scanner = scanner;
        this.bookingSystem = bookingSystem;
        this.stateHolder = stateHolder;
    }

    public void handle() throws Exception {
        bookingSystem.clearUnpaidBookings();

        Utils.clearTerminal();
        bookingSystem.printPlane();

        String choice = getChoice(scanner);

        boolean isShowingPlaneScreen = true;
        String message = null;

        while (isShowingPlaneScreen) {
            switch (choice) {
                case "L" -> {
                    stateHolder.setState(ApplicationState.FLIGHT_LIST);
                    isShowingPlaneScreen = false;
                }

                case "I" -> {
                    message = bookingSystem.getBookingInfo();
                }

                case "B" -> {
                    bookingSystem.bookASeat();
                    message = "Seat is booked";
                }

                case "S" -> {
                    try {
                        bookingSystem.changeBookingStatus();
                        message = "Booking status changed to: PAID";
                    } catch (Exception e) {
                        message = e.getMessage();
                    }
                }

                case "Q" -> System.exit(0);
            }

            Utils.clearTerminal();
            bookingSystem.printPlane();

            if (message != null) {
                System.out.println(message);
                message = null;
            }

            if (!choice.equals("L")) {
                choice = getChoice(scanner);
            }

            // clear unpaid bookings
            bookingSystem.clearUnpaidBookings();
        }
    }


    public String getChoice(final Scanner scanner) {
        KeyboardService.printBookingKeyboard();
        System.out.print("Chose action: ");
        return scanner.nextLine();
    }
}
