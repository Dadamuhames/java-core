package lessons.lesson11.pubsub;

import java.util.ArrayList;
import java.util.List;

public class ScreenService {
    private final static int DEFAULT_FORDER_LENGTH = 50;
    private final static int PADDING_LENGTH = 3;

    private final List<String> prevMessages;

    public ScreenService() {
        this.prevMessages = new ArrayList<>();
    }


    public void addMessage(final String message) {
        prevMessages.add(message);
    }

    private int getBorderLength() {
        if (prevMessages.isEmpty()) return DEFAULT_FORDER_LENGTH;

        int maxWordLength = 0;

        for (String message : prevMessages) {
            if (message.length() > maxWordLength) {
                maxWordLength = message.length();
            }
        }

        int borderLen = maxWordLength + (PADDING_LENGTH * 2);

        return Math.max(borderLen, DEFAULT_FORDER_LENGTH);

    }

    public void printScreen() {
        Utils.clearTerminal();

        int borderLength = getBorderLength();

        String padding = " ".repeat(PADDING_LENGTH);

        System.out.println("-".repeat(borderLength));

        System.out.println(getHeader(borderLength));

        System.out.println("-".repeat(borderLength));
        System.out.printf("|%s|\n", " ".repeat(borderLength - 2));

        if (prevMessages.isEmpty()) {
            System.out.printf("|%s|\n", " ".repeat(borderLength - 2));
            System.out.printf("|%s|\n", " ".repeat(borderLength - 2));
        } else {
            for (String message : prevMessages) {
                int paddingRightLen = borderLength - message.length() - PADDING_LENGTH - 2;

                String paddingRight = " ".repeat(paddingRightLen);

                System.out.printf("|%s%s%s|\n", padding, message, paddingRight);
            }
        }

        System.out.printf("|%s|\n", " ".repeat(borderLength - 2));

        System.out.println("-".repeat(borderLength));
        System.out.print("\n\n");

        System.out.print("Enter message: ");
    }


    private String getHeader(final int borderLength) {
        String headerTitle = "MESSAGES";
        int headerPaddingLength = (borderLength - headerTitle.length()) / 2;

        String headerPaddingLeft = " ".repeat(headerPaddingLength - 1);
        String headerPaddingRight = headerPaddingLeft;

        if (borderLength % 2 != 0) {
            headerPaddingRight = " ".repeat(headerPaddingLength);
        }

        return String.format("|%s%s%s|", headerPaddingLeft, headerTitle, headerPaddingRight);
    }
}
