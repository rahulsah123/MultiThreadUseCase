package com.multi.thread.multi.thread.usecase.callablefuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// Callable task to compute the square of a number
class SquareCalculator implements Callable<Integer> {
    private final int number;

    public SquareCalculator(int number) {
        this.number = number;
    }

    @Override
    public Integer call() {
        System.out.println("Calculating the square of: " + number);
        return number * number;
    }
}

public class CallableFutureExample {
    public static void main(String[] args) {
        // List of numbers to calculate squares for
        List<Integer> numbers = List.of(2, 4, 6, 8, 10);
        // Create a thread pool with a fixed number of threads
        ExecutorService executor = Executors.newFixedThreadPool(3);
        // List to hold Future objects
        List<Future<Integer>> futureResults = new ArrayList<>();
        // Submit tasks to the thread pool and collect Future objects
        for (int number : numbers) {
            Callable<Integer> task = new SquareCalculator(number);
            Future<Integer> future = executor.submit(task);
            futureResults.add(future);
        }
        // Wait for all tasks to complete and print results
        for (Future<Integer> future : futureResults) {
            try {
                // Get the result of the computation
                Integer result = future.get();
                System.out.println("Result: " + result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        // Shutdown the executor service

        executor.shutdown();
    }
}