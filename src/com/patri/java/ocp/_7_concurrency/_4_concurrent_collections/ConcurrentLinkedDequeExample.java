package com.patri.java.ocp._7_concurrency._4_concurrent_collections;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ConcurrentLinkedDequeExample {
    public static void main(String[] args) {
        Deque<Integer> deque = new ConcurrentLinkedDeque<>();
        deque.offer(10);
        deque.push(4);
        System.out.println(deque.peek());
        System.out.println(deque.pop());
        System.out.println(deque.peek());
    }
}
