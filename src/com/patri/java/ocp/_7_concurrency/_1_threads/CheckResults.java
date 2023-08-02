package com.patri.java.ocp._7_concurrency._1_threads;

//pooling = the process of checking data at some fixed interval
public class CheckResults {

    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            for (int i = 0; i < 500; i++)
                CheckResults.counter++;
        }).start();

        while (CheckResults.counter < 100) { //BAD practice => can be improved using pooling to prevent a possible infinite loop
            System.out.println("Not reached yet");
            Thread.sleep(1000); //1 sec (sleep() = pooling)
        }

        System.out.println("Reached!");
    }
}
