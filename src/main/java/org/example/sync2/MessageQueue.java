import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
package org.example.sync2;

public class MessageQueue {
    private final Queue<String> messages = new LinkedList<>();
    private final Lock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();

    public void put(String message) {
        lock.lock(); // Manually lock the door
        try {
            messages.add(message);
            notEmpty.signalAll(); // "Intercom" call: wake up receivers
        } finally {
            lock.unlock(); // Always unlock in finally to avoid getting stuck
        }
    }

    public String take() throws InterruptedException {
        lock.lock();
        try {
            while (messages.isEmpty()) {
                notEmpty.await(); // Sit and wait on the "notEmpty" condition
            }
            return messages.poll();
        } finally {
            lock.unlock();
        }
    }
}
