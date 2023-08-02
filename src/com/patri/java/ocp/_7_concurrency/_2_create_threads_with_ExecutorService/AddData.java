package com.patri.java.ocp._7_concurrency._2_create_threads_with_ExecutorService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// example for:  <T> Future<T> submit(Callable<T> task)

// Callable = functional interface that has no arguments and returns A VALUE and CAN throw a checked exception
// Runnable: the run() method returns VOID and CANNOT throw any checked exceptions
public class AddData {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = null;

        try {
            service = Executors.newSingleThreadExecutor();
            Future<Integer> result = service.submit(() -> 30 + 11);
            System.out.println(result.get());
        } finally {
            if (service != null)
                service.shutdown();
        }
    }
}
