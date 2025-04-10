package com.multi.thread.multi.thread.usecase.threadsynchronization;

public class SynchronizedCounter {

    private int counter = 0;

    // Increment the counter in a synchronized block
    public synchronized void increment() {
        counter++;
        System.out.println(Thread.currentThread().getName() + " - Counter: " + counter);
    }

    public static void main(String[] args) {
        SynchronizedCounter counter = new SynchronizedCounter();
    // Create multiple threads to increment the counter
        Runnable task = () -> {
            for (int i = 0; i < 100; i++) {
                counter.increment();
            }
        };
        Thread t1 = new Thread(task, "Thread 1");
        Thread t2 = new Thread(task, "Thread 2");
        t1.start();
        t2.start();
    }
}
