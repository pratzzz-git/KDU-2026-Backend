package com.example.sync;

import java.time.LocalTime;

public class MessageSender implements Runnable {
    private final MessageQueue queue;

    public MessageSender(MessageQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            String msg = "Msg " + i + " from " + Thread.currentThread().getName() + " at " + LocalTime.now();
            queue.put(msg);
            System.out.println("SENT: " + msg);
        }
    }
}
