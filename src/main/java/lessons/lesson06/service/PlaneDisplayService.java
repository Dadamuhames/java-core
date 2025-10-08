package lessons.lesson06.service;

import lessons.lesson06.utils.Utils;

public class PlaneDisplayService {

    public boolean isBusinessClass(final String code) {
        int col = Integer.parseInt(code.substring(1));
        return (col >= 1 && col <= 5);
    }


    public String getSeatDisplay(final String code, final boolean isBooked) {
        String seatDisplay;

        if (isBooked) {
            seatDisplay = bookedDisplay(code);
        } else {
            boolean isBusinessClass = isBusinessClass(code);

            if (isBusinessClass) {
                seatDisplay = businessClassDisplay(code);
            } else {
                seatDisplay = economyClassDisplay(code);
            }
        }

        return seatDisplay;
    }

    public String bookedDisplay(final String code) {
        return String.format("\u001B[41m[ %s ]%s ", code, Utils.RESET_COLOR);
    }


    public String businessClassDisplay(final String code) {
        return String.format("\u001B[43m[ %s ]%s ", code, Utils.RESET_COLOR);
    }

    public String economyClassDisplay(final String code) {
        return String.format("\u001B[45m[ %s ]%s ", code, Utils.RESET_COLOR);
    }
}
