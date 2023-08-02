package com.patri.java.ocp._7_concurrency._2_create_threads_with_ExecutorService;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

// example for Callable vs Supplier && checked exceptions in Callable and Runnable

// Callable functional interface is similar to the Supplier functional interface
// both their methods take no arguments and return a generic type ( Callable: V call() throws Ex , Supplier: public T get() )
// difference: the method in Callable can throw a checked exception

// How do you tell lambda expressions for these two apart? The answer is: sometimes you can't
public class AmbiguousLambdaSample {

    public static void useCallable(Callable<Integer> expression) {
    }

    public static void useSupplier(Supplier<Integer> expression) {
    }

    public static void use(Callable<Integer> expression) {
    }

    public static void use(Supplier<Integer> expression) {
    }

    public static void main(String[] args) {
        useCallable(() -> {
            throw new IOException();
        }); // COMPILES - Callable is permitted to throw checked exceptions

        /*useSupplier(() -> {
            throw new IOException();
        }); // DOES NOT COMPILE - Supplier does not support checked exceptions*/

        /*use(() -> {
            throw new IOException();
        }); // DOES NOT COMPILE (ambiguous method call) The compiler does not know which method to call, can't difference them
*/
        //ambiguity can be resolved with an explicit cast
        use((Callable<Integer>) () -> {
            throw new IOException();
        }); // COMPILES


        // checked exceptions in Callable and Runnable
        ExecutorService service = null;

        try {
            service = Executors.newSingleThreadExecutor();

            service.submit(() -> {Thread.sleep(1000); return null;}); // compiles, because it has return type and
                                                                            // the compiler sees it as a Callable expression (supports checked ex)
            /*service.submit(() -> {Thread.sleep(1000);});  // does not compile, because it doesn't have a return type and
                                                            // the compiler sees it as a Runnable expression ( does not support checked ex)*/
                                                            // Thread.sleep() throws a checked InterruptedException

            // to compile surround with try-catch
            service.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            
        } finally {
            if (service != null)
                service.shutdown();
        }
    }
}
