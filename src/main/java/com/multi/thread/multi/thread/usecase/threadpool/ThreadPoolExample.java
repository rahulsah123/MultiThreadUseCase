package com.multi.thread.multi.thread.usecase.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExample {
    // A simple task class implementing Runnable interface
    static class Task implements Runnable {
        private final int taskId;

        public Task(int taskId) {
            this.taskId = taskId;
        }

        @Override
        public void run() {

            System.out.println("Task " + taskId + " is being executed by " +
                    Thread.currentThread().getName());
            try {
                // Simulating some work with a sleep
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Task " + taskId + " was interrupted.");
            }
        }
    }

    public static void main(String[] args) {
        // Create a fixed thread pool with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);
        // Submit 10 tasks to the executor
        for (int i = 1; i <= 10; i++) {
            executor.submit(new Task(i));
        }
        // Initiate a graceful shutdown
        executor.shutdown();
        System.out.println("All tasks submitted.");
        try {
            // Wait for all tasks to finish execution
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                System.out.println("Forcing shutdown as tasks did not complete in time.");
                executor.shutdownNow(); // Force shutdown if tasks did not complete in time
            }
        } catch (InterruptedException e) {
            executor.shutdownNow(); // Force shutdown on interruption
        }
        System.out.println("Executor shutdown.");
    }
}