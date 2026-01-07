package org.example.asyncdemo;

import java.util.concurrent.Callable;

public class CallableSumTask implements Callable<Integer> {

    private int n;

    public CallableSumTask(int n) {
        this.n = n;
    }

    @Override
    public Integer call() {
        int sum = 0;

        for (int i = 1; i <= n; i++) {
            sum += i;
        }

        return sum;
    }
}
