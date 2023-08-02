package com.patri.java.ocp._7_concurrency._4_concurrent_collections;

import java.util.concurrent.*;

// BlockingQueue = is like a Queue just it has methods that have a duration of time which has to pass until the operation is executed
// if the operation is interrupted => throws InterruptedException

// LinkedBlockingQueue implements BlockingQueue which implements Queue
public class LinkedBlockingQueueANDDequeExample {
    public static void main(String[] args) {

        //LinkedBlockingQueue - linked list between elements
        //LinkedBlockingQueue implements BlockingQueue which implements Queue
        try {
            BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
            queue.offer(39);
            queue.offer(3, 4, TimeUnit.MILLISECONDS);
            System.out.println(queue.poll());
            System.out.println(queue.poll(10, TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            //handle exception
        }

        //LinkedBlockingDeque - double linked list between elements
        //LinkedBlockingDeque implements BlockingDeque which implements Deque which implements Queue
        try {
            BlockingDeque<Integer> deque = new LinkedBlockingDeque<>();
            deque.offer(91);                                            // method from Queue
            deque.offerFirst(5, 2, TimeUnit.SECONDS);           // method from BlockingDeque
            deque.offerLast(47, 100, TimeUnit.MILLISECONDS);    // method from BlockingDeque
            deque.offer(3, 4, TimeUnit.SECONDS);                // method from Deque
            System.out.println(deque.poll());
            System.out.println(deque.poll(950, TimeUnit.MILLISECONDS));
            System.out.println(deque.pollLast(200, TimeUnit.NANOSECONDS));
            System.out.println(deque.pollFirst(1, TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            //handle exception
        }

        //ConcurrentSkipListSet, ConcurrentSkipListMap are concurrent versions for TreeSet and TreeMap - they have elements(keys) sorted
        // these 2 are sorted concurrent collections
    }
}
