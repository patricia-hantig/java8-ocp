package com.patri.java.ocp._7_concurrency._2_create_threads_with_ExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// example for: waiting for all tasks to finish

// if we need the result of the task => we use get() for Future obj returned by submit()
// if we don't need the result: step 1: shutdown() - to close thread executor & step 2: awaitTermination(timeout, unit) - waits for
// a specified period of time to finish all tasks or throws an InterruptedEx
public class WaitForAllTasksToFinish {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = null;
        try {
            service = Executors.newSingleThreadExecutor();
            // add tasks to thread executor
        } finally {
            if (service != null)
                service.shutdown();                                         //step 1
        }
        if (service != null) {
            service.awaitTermination(1, TimeUnit.MINUTES);          //step 2
            // Check whether all tasks are finished
            if (service.isTerminated())                                     //step 2
                System.out.println("All tasks finished");
            else System.out.println("At least one task is still running");
        }
    }
}
