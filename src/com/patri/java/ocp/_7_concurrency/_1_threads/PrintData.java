package com.patri.java.ocp._7_concurrency._1_threads;

// execution of a thread : define thread & start thread

// defining the task of a thread can be done implementing Runnable
// starting the thread is done using start() method
public class PrintData implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Printing record: " + i);
        }
    }

    public static void main(String[] args) {
        new Thread(new PrintData()).start();
    }
}
