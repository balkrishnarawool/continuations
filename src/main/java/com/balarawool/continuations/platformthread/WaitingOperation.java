package com.balarawool.continuations.platformthread;

public class WaitingOperation {
    public static void perform(String name, int delay) {
        System.out.println(STR."Thread: \{Thread.currentThread()} Waiting for \{name} for \{delay} seconds");
        try {
            Thread.sleep(delay * 1_000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
