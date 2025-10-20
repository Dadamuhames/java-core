package lessons.lesson11.simplepool;

public class ThreadPoolApplication {
    public static void main(String[] args) {
        SimpleThreadPool threadPool = new SimpleThreadPool(10);

        for (int i = 0; i < 30; i++) {
            int n = i;

            threadPool.submit(() -> {
                System.out.printf("Task number %s\n", n);
            });
        }

        threadPool.shutdown();
    }
}
