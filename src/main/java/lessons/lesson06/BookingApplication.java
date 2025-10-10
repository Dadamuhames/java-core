package lessons.lesson06;

import lessons.lesson06.db.Booking;
import lessons.lesson06.db.BookingDbManager;
import lessons.lesson06.db.BookingRepository;
import lessons.lesson06.db.Flight;
import lessons.lesson06.db.FlightGenerator;
import lessons.lesson06.db.FlightRepository;
import lessons.lesson06.handlers.BookingStateHandler;
import lessons.lesson06.handlers.FlightStateHandler;
import lessons.lesson06.service.BookingService;
import lessons.lesson06.service.FlightService;
import lessons.lesson06.service.KeyboardService;
import lessons.lesson06.service.PlaneDisplayService;
import lessons.lesson06.service.UserInputService;
import lessons.lesson06.utils.ApplicationStateHolder;

import java.util.Map;
import java.util.Scanner;

public class BookingApplication {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ApplicationStateHolder applicationStateHolder = new ApplicationStateHolder();

            BookingService bookingService = getBookingSystem(scanner, applicationStateHolder);
            BookingStateHandler bookingStateHandler = new BookingStateHandler(scanner, bookingService, applicationStateHolder);

            FlightService flightService = getFlightService(applicationStateHolder, scanner);
            FlightStateHandler flightStateHandler = new FlightStateHandler(scanner, flightService);


            while (true) {
                switch (applicationStateHolder.getState()) {
                    case FLIGHT_LIST -> flightStateHandler.handle();

                    case BOOKING_STATE -> bookingStateHandler.handle();
                }
            }


        } catch (Exception e) {
            System.out.printf("Error - %s\n", e.getMessage());
        }
    }


    public static FlightService getFlightService(final ApplicationStateHolder applicationStateHolder, final Scanner scanner) {
        Map<Integer, Flight> flights = FlightGenerator.getFlights();

        FlightRepository flightRepository = new FlightRepository(flights);

        return new FlightService(applicationStateHolder, flightRepository, scanner);
    }


    public static BookingService getBookingSystem(final Scanner scanner, final ApplicationStateHolder applicationStateHolder) throws Exception {
        Map<Integer, Map<String, Booking>> data = BookingDbManager.getData();

        BookingRepository bookingRepository = new BookingRepository(data);

        UserInputService userInputService = new UserInputService(scanner, bookingRepository);

        PlaneDisplayService planeDisplayService = new PlaneDisplayService();

        return new BookingService(userInputService, bookingRepository, planeDisplayService, applicationStateHolder);
    }
}
