package com.patri.java.ocp._7_concurrency._1_threads;

public class Combination {

    public static void main(String[] args) {
        System.out.println("begin");
        new ReadInventoryThread().start();
        new Thread(new PrintData()).start();
        new ReadInventoryThread().start();
        System.out.println("end");
    }
}
