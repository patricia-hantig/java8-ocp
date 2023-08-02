package com.patri.java.ocp._7_concurrency._2_create_threads_with_ExecutorService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// example for: thread pool = group of reusable, pre-instantiable threads which are available to execute different tasks

// methods for Executors:
// FOR SINGLE-THREAD
// newSingleThreadExecutor() - returns ExecutorService and creates a single-threaded executor with only one thread; the results are processed
//                              in order in which they were submitted
// newSingleThreadScheduledExecutor() - returns ScheduledExecutorService and creates a single-threaded executor that schedules commends
// to run after a specified delay/period

// PLUS NEW FOR POOL THREAD:
// newCachedThreadPool() - returns ExecutorService and creates a thread Pool which creates threads, but it reuses the existed ones
// newFixedThreadPool(int nThreads) - returns ExecutorService and creates a thread Pool which uses a fixed number of threads
// newScheduledThreadPool(int nThreads) - returns ScheduledExecutorService and creates a thread Pool which schedules commends to run after
//                                        a specified delay/period and uses a fixed number of threads

public class IncreasingConcurrencyWithPools {

    // The difference between a single-thread and a pooled-thread executor is:
    // a single-thread executor will wait for an available thread to become available before running the next task
    // a pooled-thread executor can execute the next task concurrently; if the pool runs out of available threads, the task will be
    // queued by the thread executor and wait to be completed

    public static void main(String[] args) {
        ScheduledExecutorService service = null;
        try {
            service = Executors.newScheduledThreadPool(10);
            Runnable command = () -> System.out.println("Runnable command");
            service.scheduleAtFixedRate(command, 3 , 1 , TimeUnit.MINUTES);

            // Choosing a Pool Size: can be chosen based on nr of available CPUs
            int availableCPUs = Runtime.getRuntime().availableProcessors();
            System.out.println("Number of available CPUs: " + availableCPUs);
        } finally {
            if (service != null)
                service.shutdown();
        }
    }
}
