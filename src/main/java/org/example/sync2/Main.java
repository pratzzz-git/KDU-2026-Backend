import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
package org.example.sync2;

public class Main {
    public static void main(String[] args) {
        MessageQueue sharedQueue = new MessageQueue();

        // 1. Create separate pools for Senders and Receivers
        ExecutorService senderPool = Executors.newFixedThreadPool(3);
        ExecutorService receiverPool = Executors.newFixedThreadPool(3);

        // 2. Submit 3 tasks to each pool
        for (int i = 1; i <= 3; i++) {
            senderPool.submit(new MessageSender(sharedQueue));
            receiverPool.submit(new MessageReceiver(sharedQueue));
        }

        // 3. Graceful Shutdown sequence
        shutdownPool(senderPool, "SenderPool");
        shutdownPool(receiverPool, "ReceiverPool");
    }

    private static void shutdownPool(ExecutorService pool, String name) {
        pool.shutdown(); // Stop accepting new tasks
        try {
            // Wait up to 10 seconds for tasks to finish
            if (!pool.awaitTermination(10, TimeUnit.SECONDS)) {
                pool.shutdownNow(); // Force quit if taking too long
            }
            System.out.println(name + " shut down successfully.");
        } catch (InterruptedException e) {
            pool.shutdownNow();
        }
    }
}
