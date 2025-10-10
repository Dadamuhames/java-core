package lessons.lesson06.db;

import lessons.lesson06.utils.BookingStatus;
import lessons.lesson06.utils.PlaceClassEnum;
import lessons.lesson06.exceptions.DatabaseCorruptedException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingDbManager {
    private final static String DB_FILE_PATH = "bookingDb.txt";

    private static boolean createFileIfNotExists() throws IOException {
        File file = new File(DB_FILE_PATH);

        boolean fileCreated = false;

        if (!file.exists()) {
            boolean fileCanBeCreated = file.createNewFile();

            if (!fileCanBeCreated) throw new IOException("Db file cannot be created");

            fileCreated = true;
        }

        return fileCreated;
    }

    public static Map<Integer, Map<String, Booking>> getData() throws IOException, DatabaseCorruptedException {
        createFileIfNotExists();

        Map<Integer, Map<String, Booking>> data = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(DB_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Booking booking = createBookingClassFromDbString(line);

                if (!data.containsKey(booking.getFlightId())) {
                    Map<String, Booking> bookingMap = new HashMap<>();
                    bookingMap.put(booking.getCode(), booking);
                    data.put(booking.getFlightId(), bookingMap);
                } else {
                    data.get(booking.getFlightId()).put(booking.getCode(), booking);
                }
            }
        }

        return data;
    }

    private static Booking createBookingClassFromDbString(final String dbString) throws DatabaseCorruptedException {
        String[] lineParts = dbString.split(";");

        if (lineParts.length != 6) throw new DatabaseCorruptedException("Database corrupted");

        PlaceClassEnum placeClass = PlaceClassEnum.valueOf(lineParts[2]);
        Integer flightId = Integer.parseInt(lineParts[3]);
        LocalDateTime bookedAt = LocalDateTime.parse(lineParts[4]);
        BookingStatus bookingStatus = BookingStatus.valueOf(lineParts[5]);

        return new Booking(lineParts[0], lineParts[2], placeClass, flightId, bookedAt, bookingStatus);
    }

    public static void storeBookingInfo(final Booking booking) throws IOException {
        createFileIfNotExists();

        String dataString = booking.toDbString();

        try (FileWriter fileWriter = new FileWriter(DB_FILE_PATH, true); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(dataString);
            bufferedWriter.newLine();
        }
    }

    public static void updateBookingInfo(final Booking booking) throws IOException {
        boolean fileCreated = createFileIfNotExists();

        if (fileCreated) {
            storeBookingInfo(booking);
            return;
        }

        String dataString = booking.toDbString();
        String bookingStringPrefix = String.format("%s;", booking.getCode());

        File dbFile = new File(DB_FILE_PATH);
        File tempFile = File.createTempFile("bookingDbReplace", ".tmp");

        try (FileReader fileReader = new FileReader(dbFile); BufferedReader reader = new BufferedReader(fileReader); FileWriter fileWriter = new FileWriter(tempFile); BufferedWriter writer = new BufferedWriter(fileWriter)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(bookingStringPrefix)) {
                    line = dataString;
                }

                writer.write(line);
                writer.newLine();
            }
        }

        boolean renamed = tempFile.renameTo(dbFile);
        if (!renamed) throw new IOException("Booking update failed");
    }

    public static void bulkDeleteBookingInfo(final List<Booking> bookings) throws IOException {
        boolean fileCreated = createFileIfNotExists();

        if (fileCreated) {
            bulkCreateBookingInfo(bookings);
            return;
        }

        List<String> prefixList = bookings.stream().map(b -> String.format("%s", b.getCode())).toList();

        File dbFile = new File(DB_FILE_PATH);
        File tempFile = File.createTempFile("bookingDbReplace", ".tmp");

        try (FileReader fileReader = new FileReader(dbFile); BufferedReader reader = new BufferedReader(fileReader); FileWriter fileWriter = new FileWriter(tempFile); BufferedWriter writer = new BufferedWriter(fileWriter)) {

            String line;
            while ((line = reader.readLine()) != null) {
                String prefix = line.split(";")[0];

                if (prefixList.contains(prefix)) continue;

                writer.write(line);
                writer.newLine();
            }
        }

        boolean renamed = tempFile.renameTo(dbFile);
        if (!renamed) throw new IOException("Booking update failed");
    }

    private static void bulkCreateBookingInfo(final List<Booking> bookings) throws IOException {
        for (Booking booking : bookings) {
            String dataString = booking.toDbString();

            try (FileWriter fileWriter = new FileWriter(DB_FILE_PATH, true); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                bufferedWriter.write(dataString);
                bufferedWriter.newLine();
            }
        }
    }
}
