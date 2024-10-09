package com.balarawool.continuations.platformthread;

public class Demo {
    public static final Scheduler SCHEDULER = new Scheduler();

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {

            Runnable r1 = () -> {
                System.out.println("1.1");
                System.out.println("1.2");
                WaitingOperation.perform("Network", 2);
                System.out.println("1.3");
                System.out.println("1.4");
            };
            Runnable r2 = () -> {
                System.out.println("2.1");
                System.out.println("2.2");
                WaitingOperation.perform("DB", 5);
                System.out.println("2.3");
                System.out.println("2.4");
            };
            SCHEDULER.schedule(r1);
            SCHEDULER.schedule(r2);

        }
    }
}
