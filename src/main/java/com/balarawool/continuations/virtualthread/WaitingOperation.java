package com.balarawool.continuations.virtualthread;

import jdk.internal.vm.Continuation;

import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.balarawool.continuations.virtualthread.Demo.SCHEDULER;
import static com.balarawool.continuations.virtualthread.VirtualThread.SCOPE;
import static com.balarawool.continuations.virtualthread.VirtualThreadScheduler.CURRENT_VIRTUAL_THREAD;

public class WaitingOperation {
    private static final ScheduledExecutorService EXECUTOR = Executors.newSingleThreadScheduledExecutor();

    public static void perform(String name, int delay) {
        var virtualThread = CURRENT_VIRTUAL_THREAD.get();
        System.out.println("Virtual Thread:" + virtualThread.hashCode() + " Waiting for " + name + " for "+ delay +" seconds");

        var timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SCHEDULER.schedule(virtualThread);
                timer.cancel();
            }
        }, delay*1_000L);

//        EXECUTOR.schedule(() -> { SCHEDULER.schedule(virtualThread); }, delay, TimeUnit.SECONDS);

        Continuation.yield(SCOPE);
    }
}
