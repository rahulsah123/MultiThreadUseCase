package com.multi.thread.multi.thread.usecase.multithreadsort;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class MultithreadedMergeSort {
    // Merge two sorted subarrays
    private static void merge(int[] array, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        // Merge left and right arrays
        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }
        // Copy remaining elements from left array
        while (i < left.length) {
            array[k++] = left[i++];
        }
        // Copy remaining elements from right array
        while (j < right.length) {
            array[k++] = right[j++];
        }
    }

    // Parallel merge sort using ForkJoin framework
    static class ParallelMergeSortTask extends RecursiveTask<int[]> {
        private int[] array;

        public ParallelMergeSortTask(int[] array) {
            this.array = array;
        }

        @Override
        protected int[] compute() {
            if (array.length <= 1) {
                return array;
            }
            int mid = array.length / 2;
            // Split the array into left and right halves
            int[] left = Arrays.copyOfRange(array, 0, mid);
            int[] right = Arrays.copyOfRange(array, mid, array.length);

            // Sort both halves in parallel
            ParallelMergeSortTask leftTask = new ParallelMergeSortTask(left);
            ParallelMergeSortTask rightTask = new ParallelMergeSortTask(right);
            leftTask.fork(); // Fork the left task
            right = rightTask.compute(); // Compute right half
            left = leftTask.join(); // Wait for left task to finish
            // Merge sorted halves
            merge(array, left, right);
            return array;
        }
    }

    public static void main(String[] args) {
        int[] array = {38, 27, 43, 3, 9, 82, 10};
        // Create ForkJoinPool for parallel execution
        ForkJoinPool pool = new ForkJoinPool();
        // Invoke the merge sort task
        ParallelMergeSortTask task = new ParallelMergeSortTask(array);
        pool.invoke(task);
        // Print sorted array
        System.out.println("Sorted Array: " + Arrays.toString(array));
    }
}
