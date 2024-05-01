package com.balarawool.continuations.platformthread;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Scheduler {
    private ExecutorService executor = Executors.newFixedThreadPool(10);
    public void schedule(Runnable runnable) {
        executor.submit(runnable);
    }
}
