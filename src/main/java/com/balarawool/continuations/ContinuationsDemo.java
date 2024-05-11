package com.balarawool.continuations;

import jdk.internal.vm.Continuation;
import jdk.internal.vm.ContinuationScope;

public class ContinuationsDemo {
    public static void main(String[] args) {
        var cont = getContinuation();
        cont.run();
        System.out.println("Do something");
        cont.run();
        System.out.println("Do something else");
        cont.run();
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

    private static Runnable getRunnable() {
        Runnable runnable = () -> {
            System.out.println("A");
            System.out.println("B");
            System.out.println("C");
        };
        return runnable;
    }

}
