package com.balarawool.continuations.platformthread;

import jdk.internal.vm.Continuation;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static com.balarawool.continuations.virtualthread.Demo.SCHEDULER;
import static com.balarawool.continuations.virtualthread.VirtualThread.SCOPE;
import static com.balarawool.continuations.virtualthread.VirtualThreadScheduler.CURRENT_VIRTUAL_THREAD;

public class WaitingOperation {
    public static void perform(String name, int delay) {
        System.out.println("Thread: "+Thread.currentThread()+" Waiting for " + name + " for "+ delay +" seconds");
        try {
            Thread.sleep(delay * 1_000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
