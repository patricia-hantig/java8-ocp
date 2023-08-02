package com.patri.java.ocp._7_concurrency._1_threads;

// define a thread task
public class CalculatesAverages implements Runnable {

    private double[] scores;

    public CalculatesAverages(double[] scores) {
        this.scores = scores;
    }

    @Override
    public void run() {
        //work here that uses scores obj
    }
}
