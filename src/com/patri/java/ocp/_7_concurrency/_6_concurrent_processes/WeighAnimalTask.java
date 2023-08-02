package com.patri.java.ocp._7_concurrency._6_concurrent_processes;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

// example: compute the sum of all weight values while processing the data, let's suppose that there are 10 animals
public class WeighAnimalTask extends RecursiveTask {

    private int start;
    private int end;
    private Double[] weights;

    public WeighAnimalTask(int start, int end, Double[] weights) {
        this.start = start;
        this.end = end;
        this.weights = weights;
    }

    @Override
    protected Double compute() {
        if (end - start <= 3) {                         // base process
            double sum = 0;
            for (int i = start; i < end; i++) {
                weights[i] = (double) new Random().nextInt(100);
                System.out.println("Animal weighed: " + i);
                sum += weights[i];
            }
            return sum;
        }  else {                                      // recursive process
            // we use fork() and join() command to retrieve the recursive data
            int middle = start + ((end - start) / 2);
            System.out.println("[start = " + start + ", middle = " + middle + ", end = " + end + "]");

            // multi-threaded
            RecursiveTask<Double> otherTask = new WeighAnimalTask(start, middle, weights);
            otherTask.fork();                                                               // fork() completes the task in a separate thread
            return new WeighAnimalTask(middle, end, weights).compute() + otherTask.join(); // join() makes the current thread to wait for results

            // we compute the [middle, end] range using the current thread, since we already have one available
            // and the [start, middle] range using a separate thread => We then combine the results, waiting for the otherTask to complete

            // single-threaded
            /* RecursiveTask<Double> otherTask2 = new WeighAnimalTask(start, middle, weights);
            Double otherResult = otherTask2.fork().join();
            return new WeighAnimalTask(middle, end, weights).compute() + otherResult; */

            // the current thread calls join(), causing it to wait for the [start,middle] sub-task to finish before starting on
            // the [middle,end] sub-task
            // here the results are performed in a single-threaded manner

            // !!!PAY ATTENTION: make sure that fork() is called before the current thread begins a sub-task and that join() is called
            // after it finishes retrieving the results => in order for them to be done in parallel
        }
    }

    public static void main(String[] args) {
        Double[] weights = new Double[10];
        ForkJoinTask<?> task = new WeighAnimalTask(0, weights.length, weights);
        ForkJoinPool pool = new ForkJoinPool();             // step 2. Create the ForkJoinPool
        Double sum = (Double) pool.invoke(task);            // step 3. Start the ForkJoinTask

        // print results
        System.out.print("Weights: ");
        Arrays.asList(weights).stream().forEach(d -> System.out.print(d.intValue() + " "));
        System.out.println("Sum is:  " + sum);

        // the process was started as a single task and there were created additional concurrent tasks to split up the work
        // after it has already started => the order of the output can not be guaranteed.
    }
}
