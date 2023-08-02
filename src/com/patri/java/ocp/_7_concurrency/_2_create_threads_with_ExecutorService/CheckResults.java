package com.patri.java.ocp._7_concurrency._2_create_threads_with_ExecutorService;

import java.util.concurrent.*;

// example for:  Future<?> submit(Runnable task)

// How do we know when a task submitted to an ExecutorService is complete?
// submit() method returns a java.util.concurrent.Future<V> object that can be used to determine this result
public class CheckResults {
    private static int counter = 0;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = null;

        try {
            service = Executors.newSingleThreadExecutor();
            Future<?> result = service.submit(() -> {
                for (int i = 0; i < 500; i++)
                    CheckResults.counter++;
            });
            Object o = result.get(10, TimeUnit.SECONDS);
            System.out.println("Reached ");
            System.out.println("Result of the get() method is: " + o);  // As Future<V> is a generic class, the type V
                                                                        // is determined by the return type of the Runnable method
                                                                        // the return type Runnable.run() is void
        } catch (TimeoutException e) {
            System.out.println("Not reached in time");
        } finally {
            if (service != null)
                service.shutdown();
        }
    }
}
