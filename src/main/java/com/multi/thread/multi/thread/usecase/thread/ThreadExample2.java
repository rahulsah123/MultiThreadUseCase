package com.multi.thread.multi.thread.usecase.thread;

/*
 * Create Thread using Runnable interface
 */

public class ThreadExample2 implements Runnable {

    private String threadName;

    public ThreadExample2(String name) {
        this.threadName = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(threadName + " is running: " + i);
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new ThreadExample2("Thread 1"));
        Thread t2 = new Thread(new ThreadExample2("Thread 2"));
        t1.start();
        t2.start();
    }
}
