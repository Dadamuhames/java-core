package lessons.lesson11.simplepool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimpleThreadPool {
    private final BlockingQueue<Runnable> tasks;
    private final List<Thread> workers;
    private final AtomicBoolean isRunning;
    static final Runnable STOPPER = () -> {};

    public SimpleThreadPool(int workerCount) {
        this.isRunning = new AtomicBoolean(true);
        this.tasks = new LinkedBlockingDeque<>();
        this.workers = new ArrayList<>();

        for (int i = 0; i < workerCount; i++) {
            String workerName = "Worker " + (i + 1);

            Thread worker = new Thread(() -> {
                while (isRunning.get() || !tasks.isEmpty()) {
                    try {
                        Runnable task = tasks.poll(2, TimeUnit.SECONDS);

                        if (task != null && !task.equals(STOPPER)) {
                            task.run();
                        }
                    } catch (InterruptedException e) {
                        System.out.printf("Error - %s", e.getMessage());
                    }
                }
            }, workerName);

            workers.add(worker);
            worker.start();
        }

    }

    public void submit(final Runnable task) {
        tasks.add(task);
    }

    public void shutdown() {
        isRunning.set(false);

        for (int i = 0; i < workers.size(); i++) {
            tasks.add(STOPPER);
        }
    }
}
