package lessons.lesson11.pubsub;

import java.util.concurrent.BlockingDeque;

public class Publisher {
  private final BlockingDeque<String> messages;

  public Publisher(BlockingDeque<String> messages) {
    this.messages = messages;
  }

  public void add(final String message) {
    messages.add(message);
  }
}
