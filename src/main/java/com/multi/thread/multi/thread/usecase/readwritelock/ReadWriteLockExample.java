package com.multi.thread.multi.thread.usecase.readwritelock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
    private List<Integer> sharedList = new ArrayList<>();
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void writeToList(int value) {
        rwLock.writeLock().lock();
        try {
            sharedList.add(value);
            System.out.println(Thread.currentThread().getName() + " wrote: " + value);
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public void readFromList() {
        rwLock.readLock().lock();
        try {
            if (!sharedList.isEmpty()) {
                System.out.println(Thread.currentThread().getName() + " reading: " + sharedList);
            } else {
                System.out.println(Thread.currentThread().getName() + " found the list empty.");
            }
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLockExample example = new ReadWriteLockExample();
        // Writing thread (only one thread will write at a time)
        Thread writer1 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                example.writeToList(i);
                try {
                    Thread.sleep(500); // Simulate some delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Writer-1");
        // Reading threads (multiple threads can read concurrently)
        Thread reader1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {

                example.readFromList();
                try {
                    Thread.sleep(300); // Simulate some delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Reader-1");
        Thread reader2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                example.readFromList();
                try {
                    Thread.sleep(300); // Simulate some delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Reader-2");
        // Start the threads
        writer1.start();
        reader1.start();
        reader2.start();
        // Wait for threads to finish
        try {
            writer1.join();
            reader1.join();
            reader2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}