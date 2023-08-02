package com.patri.java.ocp._7_concurrency._6_concurrent_processes;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

// example: measure the weight of all the animals in the zoo, let's suppose that there are 10 animals
public class WeighAnimalAction extends RecursiveAction {

    private int start;
    private int end;
    private Double[] weights;

    public WeighAnimalAction(int start, int end, Double[] weights) {
        this.start = start;
        this.end = end;
        this.weights = weights;
    }

    @Override
    protected void compute() {                          // step 1: Create a ForkJoinTask - here we have to declare the recursive process
        if (end - start <= 3) {                         // base process
            for (int i = start; i < end; i++) {
                weights[i] = (double) new Random().nextInt(100);
                System.out.println("Animal weighed: " + i);
            }
        } else {                                        // recursive process
            int middle = start + ((end - start) / 2);
            System.out.println("[start = " + start + ", middle = " + middle + ", end = " + end + "]");
            invokeAll(new WeighAnimalAction(start, middle, weights), new WeighAnimalAction(middle, end, weights)); // Dividing tasks into
                                                                        // recursive subtasks may not always result in evenly divided sets
            // one zoo worker may end up with three animals to weigh, while others may have only one animal to weigh
            // goal of the fork/join framework = to break up large tasks into smaller ones, not to guarantee every base case ends up
            // being exactly the same size
        }
    }

    public static void main(String[] args) {
        Double[] weights = new Double[10];
        ForkJoinTask<?> task = new WeighAnimalAction(0, weights.length, weights);
        ForkJoinPool pool = new ForkJoinPool();             // step 2. Create the ForkJoinPool
        pool.invoke(task);                                  // step 3. Start the ForkJoinTask

        // print results
        System.out.print("Weights: ");
        Arrays.asList(weights).stream().forEach(d -> System.out.print(d.intValue() + " "));

        // the process was started as a single task and there were created additional concurrent tasks to split up the work
        // after it has already started => the order of the output can not be guaranteed.
    }
}
