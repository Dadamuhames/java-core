package lessons.lesson06.service;

import lessons.lesson06.db.Flight;
import lessons.lesson06.db.FlightRepository;
import lessons.lesson06.utils.ApplicationState;
import lessons.lesson06.utils.ApplicationStateHolder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class FlightService {
    private final ApplicationStateHolder applicationStateHolder;
    private final FlightRepository flightRepository;
    private final Scanner scanner;

    public FlightService(ApplicationStateHolder applicationStateHolder, FlightRepository flightRepository, Scanner scanner) {
        this.applicationStateHolder = applicationStateHolder;
        this.flightRepository = flightRepository;
        this.scanner = scanner;
    }

    public void printFlightList() {
        System.out.println("====== AVAILABLE FLIGHTS ======\n");

        LocalDateTime now = LocalDateTime.now();

        List<Flight> flights = flightRepository.findByFlightAtBefore(now);

        System.out.println("==============");

        for (Flight flight : flights) {
            System.out.println(flight.toString());
            System.out.println("==============");
        }
    }

    public Flight getFlightInfo() throws Exception {
        System.out.print("Enter flight id: ");
        int flightId = scanner.nextInt();
        scanner.nextLine();

        return flightRepository.findById(flightId).orElseThrow(() -> new Exception("Flight id invalid"));
    }


    public void switchToFlightInfo() {
        try {
            Flight flight = getFlightInfo();
            applicationStateHolder.setCurrentFlight(flight);
            applicationStateHolder.setState(ApplicationState.BOOKING_STATE);
        } catch (Exception e) {
            System.out.printf("Error - %s\n", e.getMessage());
        }
    }
}
