package com.patri.java.ocp._7_concurrency._2_create_threads_with_ExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// example for: execute()

// create threads using ExecutorService - it creates and executes threads for us
// ExecutorService is an interface => concurrency API has a factory class Executors that can be used to create an ExecutorService obj
// method newSingleThreadExecutor() - used to get an ExecutorService instance
// method execute() - used to run different asynchronous tasks
// method signature: void execute(Runnable command) - Runnable = functional interface that has no arguments and DOES NOT return data
public class ZooInfo {
    public static void main(String[] args) { // main is an independent thread
        ExecutorService service = null;
        try {
            service = Executors.newSingleThreadExecutor(); // we have only one thread
            System.out.println("begin");
            service.execute(() -> System.out.println("Printing zoo inventory")); // execute() contains one runnable based lambda expression
            service.execute(() -> {
                for (int i = 0; i < 3; i++)
                    System.out.println("Printing record: " + i);
            });
            service.execute(() -> System.out.println("Printing zoo inventory"));
            System.out.println("end");
        } finally {
            if (service != null)
                service.shutdown(); // we have to call shutdown() method because a thread executor creates a non-daemon thread
                                    // on the first task that is executed and if we don't call this method the application will never end
                                    // ExecutorService life cycle
            // shutdown() doesn't stop tasks that have already submitted to the thread executor
            // if we want to cancel all running and upcoming tasks we use - shutdownNow() (it attempts to cancel all running tasks)
            // shutdownNow() - possible to create a thread which doesn't end & returns a List<Runnable> with all the tasks that
            // were submitted in thread executor but didn't run

            // ExecutorService interface does NOT implement AutoClosable => we can't use try-with-resources
            // good practice: use a finally block to do shutdown()
            // !!!!! it does not work for thread executors that are persistent throughout the life of the application
        }
    }
}
