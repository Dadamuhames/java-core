package lessons.lesson06;

import lessons.lesson06.db.Booking;
import lessons.lesson06.db.BookingDbManager;
import lessons.lesson06.db.BookingRepository;
import lessons.lesson06.service.PlaneDisplayService;
import lessons.lesson06.service.UserInputService;
import lessons.lesson06.utils.Utils;

import java.util.Map;
import java.util.Scanner;

public class BookingApplication {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            BookingSystem bookingSystem = getBookingSystem(scanner);

            bookingSystem.printPlane();
            printKeyboard();
            System.out.print("Choose action: ");

            while (true) {
                String choice = scanner.nextLine();

                switch (choice) {
                    case "I" -> {
                        bookingSystem.getBookingFlight();
                        printKeyboard();
                        System.out.print("Choose action: ");
                    }

                    case "B" -> {
                        bookingSystem.bookASeat();
                        Utils.clearTerminal();
                        bookingSystem.printPlane();
                        printKeyboard();
                        System.out.print("Choose action: ");
                    }

                    case "Q" -> System.exit(0);
                }
            }


        } catch (Exception e) {
            System.out.printf("Error - %s\n", e.getMessage());
        }
    }

    public static void printKeyboard() {
        System.out.println("\n[I] - Get booking info | [B] - Book a seat | [Q] - Quit");
    }


    public static BookingSystem getBookingSystem(final Scanner scanner) throws Exception {
        Map<String, Booking> data = BookingDbManager.getData();

        BookingRepository bookingRepository = new BookingRepository(data);

        UserInputService userInputService = new UserInputService(scanner, bookingRepository);

        PlaneDisplayService planeDisplayService = new PlaneDisplayService();

        return new BookingSystem(userInputService, bookingRepository, planeDisplayService);
    }
}
