package com.balarawool.continuations;

import java.util.concurrent.Executors;

public class ThreadExamples {
    public static void threads() {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            executor.submit(() -> System.out.println(Thread.currentThread()));
            executor.submit(() -> System.out.println(Thread.currentThread()));
            executor.submit(() -> System.out.println(Thread.currentThread()));
            executor.submit(() -> System.out.println(Thread.currentThread()));
            executor.submit(() -> System.out.println(Thread.currentThread()));
            executor.submit(() -> System.out.println(Thread.currentThread()));
            executor.submit(() -> System.out.println(Thread.currentThread()));
            executor.submit(() -> System.out.println(Thread.currentThread()));
            executor.submit(() -> System.out.println(Thread.currentThread()));
        }
    }
}
