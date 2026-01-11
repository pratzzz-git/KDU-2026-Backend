package com.example.sync;

import java.util.LinkedList;
import java.util.Queue;

public class MessageQueue {
    private final Queue<String> messages = new LinkedList<>();

    // Adds a message to the queue
    public synchronized void put(String message) {
        messages.add(message);
        // Wake up any waiting receivers because there is now data
        notifyAll();
    }

    // Removes and returns a message
    public synchronized String take() throws InterruptedException {
        // Rule: If empty, the receiver must wait
        while (messages.isEmpty()) {
            wait();
        }
        return messages.poll();
    }
}
