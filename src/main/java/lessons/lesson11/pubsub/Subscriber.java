package lessons.lesson11.pubsub;

import java.util.concurrent.BlockingDeque;

public class Subscriber implements Runnable {
    private final BlockingDeque<String> messages;
    private final ScreenService screenService;


    public Subscriber(BlockingDeque<String> messages, ScreenService screenService) {
        this.messages = messages;
        this.screenService = screenService;
    }

    @Override
    public void run() {
        while (true) {
            if (messages.isEmpty()) continue;

            String message = messages.poll();

            if ("exit".equals(message)) break;

            screenService.addMessage(message);

            screenService.printScreen();
        }
    }
}
