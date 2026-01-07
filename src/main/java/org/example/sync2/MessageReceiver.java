package org.example.sync2;


public class MessageReceiver implements Runnable {

    private final MessageQueue queue;

    public MessageReceiver(MessageQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                String msg = queue.take();
                System.out.println("RECEIVED by " + Thread.currentThread().getName() + ": " + msg);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
