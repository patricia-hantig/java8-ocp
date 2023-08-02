package com.patri.java.ocp._7_concurrency._6_concurrent_processes;

public class ApplyForkJoinFrameworkExample {

    // when we don't know how may threads do we need = > we can split the task into multiple other tasks using the fork/join framework
    // Fork/Join Framework is based on recursion
    // recursive solution = base case & recursive case
    public static int factorial(int n) {
        if (n <= 1) return 1;               // base case        - if base case is not reached => StackOverflowError
        else return n * factorial(n-1); // recursive case
    }

    // example: measure the weight of all the animals in the zoo, let's suppose that there are 10 animals
    Double[] weights = new Double[10];
    // if a worker can measure 3 animals in 1 hour and this task needs to be done in 1 h => we will need more workers
    // They perform a recursive step by dividing the set of 10 animals into 2 sets of 5 animals, one set for each zoo worker
    // The 2 zoo workers then subdivide the set until each zoo worker has at most three animals to weigh

    // Fork/Join Framework has 3 steps:
    // 1. Create a ForkJoinTask - here we have to declare the recursive process
    // 2. Create the ForkJoinPool
    // 3. Start the ForkJoinTask

    //to implement the fork/join solution we extend one of two classes, RecursiveAction and RecursiveTask (both implement JoinForkTask interface)
    // RecursiveAction vs RecursiveTask:
    // RecursiveAction - abstract class, requires to implement: void compute()
    // RecursiveTask - abstract generic class, requires to implement: generic_type compute()

    //difference between RecursiveAction and RecursiveTask is similar to difference between Runnable and Callable

    // example WeighAnimalAction.class - Fork/Join Framework implementing RecursiveAction
    // example WeighAnimalTask.class - Fork/Join Framework implementing RecursiveTask

    public static void main(String[] args) {
        System.out.println("5! = " + factorial(5));
    }

    // Tips for reviewing a Fork/Join class:

    // - the class should extend RecursiveAction / RecursiveTask
    // - if the class extends RecursiveAction -> it should override protected void compute()
    // - if the class extends RecursiveTask -> it should override protected genericType compute()
    // - invokeAll() - takes 2 instances (fork/Join class instances) + it doesn't return a result
    // - fork() - a new task is submitted to the pool (similar with submit() from thread executor)
    // - join() - called after fork() and the current thread waits for the result of the sub-task
    // - calling compute() within a compute() - causes the task to wait for the result of the sub-task
    // - fork() should be called before the current thread performs compute() and join() called to read the results afterward
    // - compute() takes no args - the constructor of the class is used to pass instructions to the task
}
