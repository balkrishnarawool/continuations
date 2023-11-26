package com.balarawool.continuations.virtualthread;

import jdk.internal.vm.Continuation;

import java.util.Timer;
import java.util.TimerTask;

import static com.balarawool.continuations.virtualthread.Demo.SCHEDULER;
import static com.balarawool.continuations.virtualthread.VirtualThread.SCOPE;
import static com.balarawool.continuations.virtualthread.VirtualThreadScheduler.CURRENT_VIRTUAL_THREAD;

public class WaitingOperation {
    public static void perform(String name, int delay)  {
        var virtualThread = CURRENT_VIRTUAL_THREAD.get();

        System.out.println("Waiting on "+name+" for "+delay+" seconds in VirtualThread:"+virtualThread.hashCode());
        var timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SCHEDULER.schedule(virtualThread);
                timer.cancel();
            }
        }, delay * 1_000L);

        Continuation.yield(SCOPE);
    }
}
