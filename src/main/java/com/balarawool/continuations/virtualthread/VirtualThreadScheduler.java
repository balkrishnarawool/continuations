package com.balarawool.continuations.virtualthread;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualThreadScheduler {
    public static ScopedValue<VirtualThread> CURRENT_VIRTUAL_THREAD = ScopedValue.newInstance();

    private Queue<VirtualThread> queue = new ConcurrentLinkedQueue<>();
    private ExecutorService executor = Executors.newFixedThreadPool(10);

    public void schedule(VirtualThread virtualThread) {
        queue.add(virtualThread);
    }

    public void start() {
        while(true) {
            if (!queue.isEmpty()) {
                var vt = queue.remove();
                executor.submit(() ->
                        ScopedValue.where(CURRENT_VIRTUAL_THREAD, vt)
                                .run(vt::run));
            }
        }
    }
}
