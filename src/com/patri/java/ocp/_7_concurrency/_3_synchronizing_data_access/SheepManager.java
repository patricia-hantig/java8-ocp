package com.patri.java.ocp._7_concurrency._3_synchronizing_data_access;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// example for: lack of thread synchronizing

// thread safety = property of an object to assure safety execution for threads when they run in the same time
public class SheepManager {

    private int sheepCount = 0;

    private void incrementAndReport() {
        System.out.print((++sheepCount) + " ");    // 2 tasks executed in the same time
                                                    // both can execute ++sheepCounter at the same time => race condition
                                                    // operator "++" is not thread safe
    }

    public static void main(String[] args) {
        ExecutorService service = null;

        try {
            service = Executors.newFixedThreadPool(20);
            SheepManager manager = new SheepManager();
            for (int i = 0; i < 10; i++)
                service.submit(() -> manager.incrementAndReport());
        } finally {
            if (service != null)
                service.shutdown();
        }
    }

    //possible outputs:    1 2 3 4 5 6 7 8 9 10
                        // 1 2 2 3 4 5 6 7 8 9  -> we have two values of 2
                        // 2 4 5 6 7 1 8 9 10 3  -> order not ok
}
