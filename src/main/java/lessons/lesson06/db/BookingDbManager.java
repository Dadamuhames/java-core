package lessons.lesson06.db;

import lessons.lesson06.utils.PlaceClassEnum;
import lessons.lesson06.exceptions.DatabaseCorruptedException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BookingDbManager {
    private final static String dbFilePath = "bookingDb.txt";

    public static Map<String, Booking> getData() throws IOException, DatabaseCorruptedException {
        Map<String, Booking> data = new HashMap<>();


        File file = new File(dbFilePath);

        if (!file.exists()) {
            boolean fileCreated = file.createNewFile();

            if (!fileCreated) throw new IOException("File cannot be created");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(dbFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineParts = line.split(";");

                if (lineParts.length != 3) throw new DatabaseCorruptedException("Database corrupted");

                PlaceClassEnum placeClass = PlaceClassEnum.valueOf(lineParts[2]);

                Booking booking = new Booking(lineParts[0], lineParts[1], placeClass);

                data.put(booking.code(), booking);
            }
        }

        return data;
    }

    public static void storeBookingInfo(final Booking booking) throws IOException {
        String dataString = String.format("%s;%s;%s", booking.code(), booking.fio(), booking.placeClass());

        try (FileWriter fileWriter = new FileWriter(dbFilePath, true); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(dataString);
            bufferedWriter.newLine();
        }
    }
}
