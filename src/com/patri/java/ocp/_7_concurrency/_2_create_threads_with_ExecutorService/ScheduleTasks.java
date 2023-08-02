package com.patri.java.ocp._7_concurrency._2_create_threads_with_ExecutorService;

import java.util.concurrent.*;

// example for: use of SchedulerExecutorService

// to schedule tasks to happen in the future -> use SchedulerExecutorService - subinterface of ExecutorService
// methods:
// schedule(Callable<V> callable, long delay, TimeUnit unit),
// schedule(Runnable command, long delay, TimeUnit unit),
// scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit),
// scheduleAtFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit)

public class ScheduleTasks {
    public static void main(String[] args) {
        ScheduledExecutorService service = null;
        try {
            service = Executors.newSingleThreadScheduledExecutor();

            Runnable task1 = () -> System.out.println("Hello");
            Callable<String> task2 = () -> "Monkey";

            // schedule(Runnable command, long delay, TimeUnit unit) - creates and executes a Runnable task after a specified delay
            Future<?> result1 = service.schedule(task1, 1, TimeUnit.SECONDS);
            // schedule(Callable<V> callable, long delay, TimeUnit unit) - creates and executes a Callable task after a specified delay
            Future<?> result2 = service.schedule(task2, 10, TimeUnit.SECONDS);

            // scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)
            // it creates a new task and submits it to ExecutorService at every specified period of time even if the first task
            // is finished or not
            // if the task takes more than the delay between tasks - it will be created a new task (we will have a set of tasks)
            Runnable command = () -> System.out.println("Runnable command");
            service.scheduleAtFixedRate(command, 5, 1, TimeUnit.MINUTES); // it executes a Runnable task each minute
                                                                                            // with an initial delay of 5 minutes

            // scheduleAtFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit)
            // it creates a new task after the first one is finished
            service.scheduleWithFixedDelay(command, 0, 2, TimeUnit.MINUTES);    // if the first task  started at 12:00
                                                                                                // it takes 5 minutes to be finished
                                                                                                // then second task will start at 12:07 (5+2)

            // ! IMPORTANT: the last 2 methods doesn't use Callable because it could generate an infinite number of Future objects
            // practical uses:
            // schedule() - to check on the state of processing a task and send out notifications if it is not finished
            // schedule() - to delay processing
            // scheduleAtFixedRate() - useful for tasks that need to be run at specific intervals
            // scheduleAtFixedDelay() - useful for processes that you want to happen repeatedly but whose specific time is unimportant
        } finally {
            if (service != null)
                service.shutdown();
        }
    }
}
