package com.multi.thread.multi.thread.usecase.diningphilosopher;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Philosopher implements Runnable {
    private final int id;
    private final Lock leftFork;
    private final Lock rightFork;

    public Philosopher(int id, Lock leftFork, Lock rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    // Simulate philosopher thinking
    private void think() throws InterruptedException {
        System.out.println("Philosopher " + id + " is thinking.");
        Thread.sleep((int) (Math.random() * 1000)); // Random think time
    }

    // Simulate philosopher eating
    private void eat() throws InterruptedException {
        System.out.println("Philosopher " + id + " is eating.");
        Thread.sleep((int) (Math.random() * 1000)); // Random eat time
    }

    // Pick up both forks (with proper lock order to prevent deadlock)
    private void pickUpForks() {
        leftFork.lock();
        rightFork.lock();
        System.out.println("Philosopher " + id + " picked up both forks.");
    }

    // Put down both forks
    private void putDownForks() {
        leftFork.unlock();
        rightFork.unlock();
        System.out.println("Philosopher " + id + " put down both forks.");
    }

    @Override
    public void run() {
        try {
            while (true) {
                think(); // Philosopher is thinking
                pickUpForks(); // Philosopher tries to pick up forks
                eat(); // Philosopher is eating
                putDownForks(); // Philosopher puts down forks
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class DiningPhilosophers {
    public static void main(String[] args) {
        int numPhilosophers = 5;
        Philosopher[] philosophers = new Philosopher[numPhilosophers];
        Lock[] forks = new ReentrantLock[numPhilosophers];
        // Initialize forks as locks
        for (int i = 0; i < numPhilosophers; i++) {
            forks[i] = new ReentrantLock();
        }
        // Initialize philosophers and assign them forks
        for (int i = 0; i < numPhilosophers; i++) {
            Lock leftFork = forks[i];
            Lock rightFork = forks[(i + 1) % numPhilosophers];
            // To avoid deadlock, the last philosopher picks up the right fork first
            if (i == numPhilosophers - 1) {
                philosophers[i] = new Philosopher(i, rightFork, leftFork);
            } else {
                philosophers[i] = new Philosopher(i, leftFork, rightFork);
            }
            // Start philosopher threads
            new Thread(philosophers[i], "Philosopher-" + i).start();
        }
    }
}
