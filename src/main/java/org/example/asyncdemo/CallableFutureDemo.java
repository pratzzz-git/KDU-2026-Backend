package org.example.asyncdemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableFutureDemo {

    public static void main(String[] args) throws Exception {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        CallableSumTask task = new CallableSumTask(10);

        Future<Integer> future = executor.submit(task);

        System.out.println("Task submitted, doing something else...");

        Integer result = future.get();   // BLOCKING

        System.out.println("Sum result: " + result);

        executor.shutdown();
    }
}
