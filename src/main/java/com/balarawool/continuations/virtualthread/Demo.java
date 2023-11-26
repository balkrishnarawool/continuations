package com.balarawool.continuations.virtualthread;

public class Demo {
    public static final VirtualThreadScheduler SCHEDULER = new VirtualThreadScheduler();

    public static void main(String[] args) {
        new Thread(SCHEDULER::start).start();

        var vt1 = new VirtualThread(() -> {
            System.out.println("1.1");
            System.out.println("1.2");
            WaitingOperation.perform("Network", 2);
            System.out.println("1.3");
            System.out.println("1.4");
            WaitingOperation.perform("Network", 5);
            System.out.println("1.5");
            System.out.println("1.6");
        });
        var vt2 = new VirtualThread(() -> {
            System.out.println("2.1");
            System.out.println("2.2");
            WaitingOperation.perform("DB", 5);
            System.out.println("2.3");
            System.out.println("2.4");
            WaitingOperation.perform("DB", 1);
            System.out.println("2.5");
            System.out.println("2.6");
        });

        SCHEDULER.schedule(vt1);
        SCHEDULER.schedule(vt2);
    }
}
