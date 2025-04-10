package com.multi.thread.multi.thread.usecase.deadlock;

public class DeadlockSimulation {
    // Two resources (locks)
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    // Method for Thread 1 that tries to lock resources in one order
    public void method1() {
        synchronized (lock1) {
            System.out.println("Thread 1: Holding lock 1...");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            System.out.println("Thread 1: Waiting for lock 2...");
            synchronized (lock2) {
                System.out.println("Thread 1: Holding lock 1 and lock 2...");
            }
        }
    }

    // Method for Thread 2 that tries to lock resources in the opposite order
    public void method2() {
        synchronized (lock2) {
            System.out.println("Thread 2: Holding lock 2...");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            System.out.println("Thread 2: Waiting for lock 1...");
            synchronized (lock1) {
                System.out.println("Thread 2: Holding lock 1 and lock 2...");
            }
        }
    }

    public static void main(String[] args) {
        DeadlockSimulation deadlock = new DeadlockSimulation();
        // Thread 1 runs method1
        Thread t1 = new Thread(() -> deadlock.method1());
        // Thread 2 runs method2
        Thread t2 = new Thread(() -> deadlock.method2());
        // Start both threads
        t1.start();
        t2.start();
    }
}