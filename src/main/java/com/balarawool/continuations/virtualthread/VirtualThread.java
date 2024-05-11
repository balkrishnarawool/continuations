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
        cont = new Continuation(SCOPE, runnable);
        id = COUNTER.getAndIncrement();
    }

    public void run() {
        System.out.println(STR."Virtual Thread #\{id} running on \{Thread.currentThread()}");
        cont.run();
    }
}
