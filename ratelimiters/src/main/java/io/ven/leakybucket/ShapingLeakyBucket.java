package io.ven.leakybucket;

import java.util.concurrent.*;
import java.util.function.Consumer;

public class ShapingLeakyBucket<T> {
    private final BlockingQueue<T> queue;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public ShapingLeakyBucket(int capacity, int leakRatePerSecond, Consumer<T> processor) {
        queue = new LinkedBlockingQueue<>(capacity);
        int delayInMicros = 1_000_000 / leakRatePerSecond;
        scheduler.scheduleAtFixedRate(() -> {
            T task = queue.poll();
            if (task != null) {
                processor.accept(task);
            }
        }, 0, delayInMicros, TimeUnit.MICROSECONDS);
    }

    public boolean submit(T request) {
        return queue.offer(request);
    }
}
