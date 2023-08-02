package com.patri.java.ocp._7_concurrency._4_concurrent_collections;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueExample {
    public static void main(String[] args) {
        Queue<Integer> queue = new ConcurrentLinkedQueue<>();
        queue.offer(31);
        System.out.println(queue.peek()); //returns first elem or null
        System.out.println(queue.poll()); //removes first elem and returns it
        System.out.println(queue.peek()); //null
    }
}
