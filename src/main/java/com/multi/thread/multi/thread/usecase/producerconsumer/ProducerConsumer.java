package com.multi.thread.multi.thread.usecase.producerconsumer;

import java.util.LinkedList;
import java.util.List;

public class ProducerConsumer {

    private List<Integer> buffer = new LinkedList<>();
    private final int LIMIT = 5;
    private final Object lock = new Object();

    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (lock) {
                while (buffer.size() == LIMIT) {
                    lock.wait();
                }
                buffer.add(value++);
                System.out.println("Produced: " + value);
                lock.notify();
            }
            Thread.sleep(1000); // Simulate some delay
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            synchronized (lock) {
                while (buffer.isEmpty()) {
                    lock.wait();
                }
                int value = buffer.remove(0);
                System.out.println("Consumed: " + value);
                lock.notify();
            }
            Thread.sleep(1500); // Simulate some delay
        }
    }

    public static void main(String[] args) {

        ProducerConsumer pc = new ProducerConsumer();

        Thread producerThread = new Thread(() -> {
            try {
                pc.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                pc.consume();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}