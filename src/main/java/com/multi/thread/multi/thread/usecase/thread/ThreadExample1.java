package com.multi.thread.multi.thread.usecase.thread;

/*
 * Create Thread using Thread class
 */

public class ThreadExample1 extends Thread {

    private String threadName;

    public ThreadExample1(String name) {
        this.threadName = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(threadName + " is running: " + i);
        }
    }

    public static void main(String[] args) {
        ThreadExample1 t1 = new ThreadExample1("Thread 1");
        ThreadExample1 t2 = new ThreadExample1("Thread 2");
        t1.start();
        t2.start();
    }
}
