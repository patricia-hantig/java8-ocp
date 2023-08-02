package com.patri.java.ocp._7_concurrency._3_synchronizing_data_access;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

// we protect data using atomic classes
// atomic = property of an operation that has to be executed without intervention of other thread
public class SheepManagerAtomic {
    private AtomicInteger sheepCount = new AtomicInteger(0); // replace int with an atomic class AtomicInteger

    private void incrementAndReport() {
        System.out.print((sheepCount.incrementAndGet()) + " "); // replace "++" operator (is not thread safe) with an atomic method
        // "++" is not atomic: it has 2 tasks: read & write
        // incrementAndGet() does both read and write operations
    }

    public static void main(String[] args) {
        ExecutorService service = null;

        try {
            service = Executors.newFixedThreadPool(20);
            SheepManagerAtomic manager = new SheepManagerAtomic();
            for (int i = 0; i < 10; i++)
                service.submit(() -> manager.incrementAndReport());
        } finally {
            if (service != null)
                service.shutdown();
        }
    }
    //possible outputs:    1 2 3 4 5 6 7 8 9 10
                        // 2 4 5 6 7 1 8 9 10 3  -> order not ok
    // we receive all numbers 1-10 but the order is still not ok, we don't loose values
}
