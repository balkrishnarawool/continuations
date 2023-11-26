package com.balarawool.continuations.virtualthread;

import jdk.internal.vm.Continuation;
import jdk.internal.vm.ContinuationScope;

public class VirtualThread {
    public static final ContinuationScope SCOPE = new ContinuationScope("VirtualThreadScope");

    private Continuation cont;

    public VirtualThread(Runnable runnable) {
        cont = new Continuation(SCOPE, runnable);
    }

    public void run() {
        System.out.println("VirtualThread:"+hashCode() + " is running on " + Thread.currentThread());
        cont.run();
    }
}
