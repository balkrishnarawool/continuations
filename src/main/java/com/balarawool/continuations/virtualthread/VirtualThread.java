package com.balarawool.continuations.virtualthread;

import jdk.internal.vm.Continuation;
import jdk.internal.vm.ContinuationScope;

import java.util.concurrent.atomic.AtomicInteger;

public class VirtualThread {
    private static final AtomicInteger COUNTER = new AtomicInteger(1);
    public static final ContinuationScope SCOPE = new ContinuationScope("VirtualThread");

    private Continuation cont;
    private int id;

    public VirtualThread(Runnable runnable) {
        id = COUNTER.getAndIncrement();
        cont = new Continuation(SCOPE, runnable);
    }

    public void run() {
        System.out.println("VirtualThread "+id+" is running on "+Thread.currentThread());
        cont.run();
    }
}
