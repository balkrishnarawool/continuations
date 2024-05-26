package com.balarawool.continuations.virtualthread;

public class Demo {
    public static final VirtualThreadScheduler SCHEDULER = new VirtualThreadScheduler();

    public static void main(String[] args) {
        new Thread(SCHEDULER::start).start();

        for (int i = 0; i < 1000; i++) {
            var vt1 = new VirtualThread(() -> {
                System.out.println("1.1");
                System.out.println("1.2");
                WaitingOperation.perform("Network", 2);
                System.out.println("1.3");
                System.out.println("1.4");
            });
            var vt2 = new VirtualThread(() -> {
                System.out.println("2.1");
                System.out.println("2.2");
                WaitingOperation.perform("DB", 5);
                System.out.println("2.3");
                System.out.println("2.4");
            });

            SCHEDULER.schedule(vt1);
            SCHEDULER.schedule(vt2);
        }
    }
}
