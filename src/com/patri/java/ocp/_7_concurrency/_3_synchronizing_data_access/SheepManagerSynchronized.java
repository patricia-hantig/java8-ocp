package com.patri.java.ocp._7_concurrency._3_synchronizing_data_access;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

// example for: synchronization + cost of it

// we use lock monitor (synchronized blocks) to make sure that only one thread is executing at a specific time
public class SheepManagerSynchronized {
    private AtomicInteger sheepCount = new AtomicInteger(0);

    /*private void incrementAndReport() {
        synchronized (this) {
            System.out.print((sheepCount.incrementAndGet()) + " ");
        }
    } */

    // commented method from above and incrementAndReport() are equivalent
    // we synchronize the execution of the threads
    // even if the threads are created and executed in the same time -> they wait in the synchronized block for the last thread to be executed
    private synchronized void incrementAndReport() {
        System.out.print((sheepCount.incrementAndGet()) + " ");
    }

    // we can have synchronize on every object
    private final Object lock = new Object();
    private int sheepCount2 = 0;
    private void incrementAndReport2() {
        synchronized (lock) {
            System.out.print((++sheepCount2) + " "); // atomic is unnecessary
        }
    }

    public static void main(String[] args) {
        ExecutorService service = null;

        try {
            service = Executors.newFixedThreadPool(20);
            SheepManagerSynchronized manager = new SheepManagerSynchronized();

            for (int i = 0; i < 10; i++)
                service.submit(() -> manager.incrementAndReport());


            /*for (int i = 0; i < 10; i++)
                synchronized (manager) {    // this doesn't fixes the problem because we synchronize the CREATION of threads and not
                                            // the EXECUTION of threads
                    service.submit(() -> manager.incrementAndReport());
                }*/
        } finally {
            if (service != null)
                service.shutdown();
        }
    }

    // we can have synchronized static methods
    public static synchronized void print() {
        System.out.println("Finish");
    }

    //output will always be: 1 2 3 4 5 6 7 8 9 10


    // example for: cost of synchronization
    // multi-threading - do multiple things at the same time
    // synchronization - implies using single-thread = protecting data with cost of performance

    // ex: we have a concurrent class with methods that synchronize the same object
    // we have 50 threads that accesses the object
    // each thread 100 milliseconds
    // 50 threads * 100 milliseconds = 5000 milliseconds = 5 seconds // to much in computer programming

}
