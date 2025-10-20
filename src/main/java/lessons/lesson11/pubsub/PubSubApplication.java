package lessons.lesson11.pubsub;

import java.util.Scanner;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class PubSubApplication {
    public static void main(String[] args) {
        Utils.clearTerminal();

        BlockingDeque<String> messages = new LinkedBlockingDeque<>();

        ScreenService screenService = new ScreenService();
        Subscriber subscriber = new Subscriber(messages, screenService);
        Publisher publisher = new Publisher(messages);

        Thread subscriberThread = new Thread(subscriber, "Subscriber");
        subscriberThread.start();

        screenService.printScreen();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String message = scanner.nextLine();

                if(message.isEmpty()) {
                    screenService.printScreen();
                    continue;
                }

                publisher.add(message);

                if ("exit".equals(message)) break;
            }
        } catch (Exception e) {
            System.out.printf("Error - %s\n", e.getMessage());
        } finally {
            System.out.println("\nExit");

            try {
                subscriberThread.join(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
