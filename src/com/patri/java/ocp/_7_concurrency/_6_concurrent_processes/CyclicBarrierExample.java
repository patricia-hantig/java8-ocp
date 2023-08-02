package com.patri.java.ocp._7_concurrency._6_concurrent_processes;


// Concurrency API - includes classes that can be used to coordinate tasks among a group of related threads
// for this we use CyclicBarrier and ForkJoinPool

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierExample {

    // creating a CyclicBarrier

    // example: cleaning zoo pens(cages)
    // we have 4 workers and the process is: we get the animals out of cage, clean the cage and then put the animals inside again
    // the workers will do their task concurrently with break between the end of a task and the beginning of the next

    // example without CyclicBarrier
    private void removeAnimals() {
        System.out.println("Removing animals");
    }

    private void cleanPen() {
        System.out.println("Cleaning the pen");
    }

    private void addAnimals() {
        System.out.println("Adding animals");
    }

    public void performTaskWithoutCyclicBarrier() {
        removeAnimals();
        cleanPen();
        addAnimals();
    }

    public void performTaskWithCyclicBarrier(CyclicBarrier c1, CyclicBarrier c2) {
        try {
            removeAnimals();
            c1.await();
            cleanPen();
            c2.await();
            addAnimals();
        } catch (InterruptedException | BrokenBarrierException e) {
            //handle exception
        }
    }

    public static void main(String[] args) {
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(4);
            CyclicBarrierExample manager = new CyclicBarrierExample();
            for (int i = 0; i < 4; i++)
                service.submit(() -> manager.performTaskWithoutCyclicBarrier());
        } finally {
            if (service != null)
                service.shutdown();
        }

        // with multiple workers the output is random: some animals are still being removed while the cage is being cleaned and
        // other animals are added before the cleaning process is finished

        // this output can be improved using CyclicBarrier class
        // CyclicBarrier class = takes in its constructors a limit value (indicating the number of threads to wait for)
        // as each thread finishes, it calls the await() method on the cyclic barrier
        // once the specified number of threads have each called await(), the barrier is released and all threads can continue

        // example with CyclicBarrier
        ExecutorService executorService = null;
        try {
            executorService = Executors.newFixedThreadPool(4);
            CyclicBarrierExample manager = new CyclicBarrierExample();
            CyclicBarrier c1 = new CyclicBarrier(4);
            CyclicBarrier c2 = new CyclicBarrier(4, () -> System.out.println("*** Pen cleaned!"));
            for (int i = 0; i < 4; i++)
                executorService.submit(() -> manager.performTaskWithCyclicBarrier(c1, c2));
        } finally {
            if (executorService != null)
                executorService.shutdown();
        } // all of the results are organized: removing animals in step 1, clean cages in step 2, add animals in cages in step 3
        // we have a loss in performance using CyclicBarrier ( for ex: one worker may be incredibly slow at removing lions, resulting
        // in the other three workers waiting for him to finish\

        //Thread Pool Size and Cyclic Barrier Limit
        // Nr of threads MUST BE AT LEAST AS LARGE as your CyclicBarrier limit value
        // if we have ExecutorService service = Executors.newFixedThreadPool(2); -> the code will hang indefinitely
        // this means that the barrier will NEVER be reached => it creates some form of DEADLOCK!!!!

        // Reusing CyclicBarrier
        // after a CyclicBarrier is broken -> all threads are released and nr of threads waiting on the CyclicBarrier == 0
        // now the CyclicBarrier can be reused for a new set of waiting threads
        // ex: if we have a CyclicBarrier with limit 5 and we have 15 threads that call await() => the CyclicBarrier will be activated 3 times
    }
}
