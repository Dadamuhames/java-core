package lessons.lesson06.service;

import lessons.lesson06.db.BookingRepository;

import java.util.Scanner;

public class UserInputService {
    private final Scanner scanner;
    private final BookingRepository bookingRepository;

    public UserInputService(Scanner scanner, BookingRepository bookingRepository) {
        this.scanner = scanner;
        this.bookingRepository = bookingRepository;
    }

    public boolean isSeatCodeValid(final String code) {
        if (code.length() < 2 || code.length() > 3) return false;

        char row = code.charAt(0);
        int col = Integer.parseInt(code.substring(1));

        boolean validBusinessRow = (col >= 1 && col <= 5) && (row >= 'A' && row <= 'F') && row != 'B' && row != 'E';
        boolean validBrokeRow = (col >= 6 && col <= 21) && (row >= 'A' && row <= 'F');

        return validBusinessRow || validBrokeRow;
    }

    public String promptSeatCodeToBook() {
        String code = promptSeatCode();

        boolean isPlaceBooked = bookingRepository.existsByCode(code);

        if (isPlaceBooked) {
            System.out.println("Place is already book. Please choose another one");
            return promptSeatCodeToBook();
        }

        return code;
    }


    public String promptSeatCode() {
        System.out.print("Enter code of seat (e.g: A1, E3): ");

        String code = scanner.nextLine();

        if (!isSeatCodeValid(code)) {
            System.out.println("Seat code invalid");
            return promptSeatCode();
        }

        return code;
    }

    public String promptFio() {
        System.out.print("Enter fio: ");

        return scanner.nextLine();
    }
}
