package com.patri.java.ocp._7_concurrency._1_threads;

// execution of a thread : define thread & start thread

// defining the task of a thread can be done extending Thread class
//starting the thread is done using start() method
public class ReadInventoryThread extends Thread {

    public void run() {
        System.out.println("Printing zoo inventory");
    }

    public static void main(String[] args) {
        new ReadInventoryThread().start();
    }
}
