package lessons.lesson06.handlers;

import lessons.lesson06.service.FlightService;
import lessons.lesson06.service.KeyboardService;
import lessons.lesson06.utils.Utils;

import java.util.Scanner;

public class FlightStateHandler {
    private final Scanner scanner;
    private final FlightService flightService;

    public FlightStateHandler(Scanner scanner, FlightService flightService) {
        this.scanner = scanner;
        this.flightService = flightService;
    }

    public void handle() {
        Utils.clearTerminal();
        flightService.printFlightList();
        KeyboardService.printFlightKeyboard();
        System.out.print("Choose action: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "I" -> flightService.switchToFlightInfo();

            case "Q" -> System.exit(0);
        }
    }
}
