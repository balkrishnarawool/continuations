package com.balarawool.continuations;

import jdk.internal.vm.Continuation;
import jdk.internal.vm.ContinuationScope;

public class ContinuationsDemo {
    public static void main(String[] args) {
        var runnable = getRunnable();
        runnable.run();

        var cont = getContinuation();
        cont.run();
        cont.run();
        cont.run();
        cont.run();
    }

    private static Runnable getRunnable() {
        Runnable runnable = () -> {
            System.out.println("A");
            System.out.println("B");
            System.out.println("C");
        };
        return runnable;
    }

    private static Continuation getContinuation() {
        var scope = new ContinuationScope("Demo");
        var cont = new Continuation(scope, () -> {
            System.out.println("A");
            Continuation.yield(scope);
            System.out.println("B");
            Continuation.yield(scope);
            System.out.println("C");
        });
        return cont;
    }
}
