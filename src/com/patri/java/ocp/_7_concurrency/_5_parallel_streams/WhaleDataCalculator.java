package com.patri.java.ocp._7_concurrency._5_parallel_streams;

import java.util.ArrayList;
import java.util.List;

// example for: understanding performance improvements
public class WhaleDataCalculator {

    // We have a task that requires processing 4,000 records, with each record taking a modest 10 milliseconds to complete

    public int processRecord(int input) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            //handle exception
        }
        return input + 1;
    }

    // Solution using normal stream
    public void processAllData(List<Integer> data) {
        data.stream().map(a -> processRecord(a)).count(); //processRecord is an independent operation = results of one operation on one
                                                        //element of the stream doesn't affect the result of another element from stream
        // each call to processRecord() can be executed separately, without impacting any other invocation of the method
    }

    // Solution using parallel stream
    public void processAllDataParallel(List<Integer> data) {
        data.parallelStream().map(a -> processRecord(a)).count();
    }

    public static void main(String[] args) {
        WhaleDataCalculator calculator = new WhaleDataCalculator();

        //Define data
        List<Integer> data = new ArrayList<Integer>();
        for (int i = 0; i < 4000; i++)
            data.add(i);

        //Process data
        long start = System.currentTimeMillis();
        calculator.processAllData(data);
        double time = (System.currentTimeMillis() - start) / 1000.0;

        //Report results
        System.out.println("Tasks completed in: " + time + " seconds (for normal stream)");

        //Process data with parallel stream
        long startParallel = System.currentTimeMillis();
        calculator.processAllDataParallel(data);
        double timeParallel = (System.currentTimeMillis() - startParallel) / 1000.0;

        //Report results with parallel stream
        System.out.println("Tasks completed in: " + timeParallel + " seconds (for parallel stream)"); // performance improved
    }
}
