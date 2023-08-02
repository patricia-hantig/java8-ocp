package com.patri.java.ocp._7_concurrency._7_threading_problems;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// deadlock example
public class Fox {

    public void eatAndDrink(Food food, Water water) {
        synchronized (food) {
            System.out.println("Got food!");
            move();
            synchronized (water) {
                System.out.println("Got water!");
            }
        }
    }

    public void drinkAndEat(Food food, Water water) {
        synchronized (water) {
            System.out.println("Got water!");
            move();
            synchronized (food) {
                System.out.println("Got food!");
            }
        }
    }

    public void move() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // create participants and resources
        Fox foxy = new Fox();
        Fox tails = new Fox();
        Food food = new Food();
        Water water = new Water();

        // process data
        ExecutorService service = null;
        try {
            service = Executors.newScheduledThreadPool(10);
            service.submit(() -> foxy.eatAndDrink(food, water));
            service.submit(() -> tails.drinkAndEat(food, water));   // here we have DEADLOCK because both participants are blocked
                                                                    // and waiting for resources that will never become available
        } finally {
            if (service != null)
                service.shutdown();
        }
    }

    // output: Got food!
    //         Got water!
    // after this infinite hang!!!!!!

    // foxy gets the food and it moves to the other side to get the water, but tails drank the water and is waiting for the food
    // to become available
}
