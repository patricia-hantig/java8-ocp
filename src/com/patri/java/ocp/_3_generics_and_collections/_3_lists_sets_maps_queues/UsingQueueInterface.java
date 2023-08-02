package com.patri.java.ocp._3_generics_and_collections._3_lists_sets_maps_queues;

import java.util.ArrayDeque;
import java.util.Queue;

public class UsingQueueInterface {
    public static void main(String[] args) {
        // Queue = is used when elements are added and removed in a specific order
        // are used for sorting elements prior to processing them
        // a queue is assumed to be FIFO, sometimes LIFO

        // ■ Queue implementations
        // ■■ LinkedList = besides being a list, it is a double-ended queue
        // +++ has methods also from List and also from Queue
        // --- isn't as efficient as a "pure" queue

        // ■■ ArrayDeque = "pure" double-ended queue
        // it stores it's elements in a resizable array
        // +++ more efficient than LinkedList

        // ■ ArrayDeque methods
        //          Method      |                                   Description                                 | For queue | For stack
        // -----------------------------------------------------------------------------------------------------------------------------
        //  boolean add(E e)    | adds an element to the back of the queue and returns true or throws exception |   Yes     |   No
        //  E element()         | returns next element or throws an exception if empty queue                    |   Yes     |   No
        //  boolean offer(E e)  | adds an element to the back of the queue and returns whether successful       |   Yes     |   No
        //  E remove()          | removes and returns next element or throws an exception if empty queue        |   Yes     |   No
        //  void push(E e)      | adds an element to the front of the queue                                     |   Yes     |   Yes
        //  E poll()            | removes and returns next element or returns null if empty queue               |   Yes     |   No
        //  E peek()            | returns next element or returns null if empty queue                           |   Yes     |   Yes
        //  E pop()             | removes and returns next element or throws exception if empty queue           |   No      |   Yes

        // except push() all are in Queue interface as well - push() is what makes it double-ended queue
        // we have 2 sets of methods - the ones that throw an exception if something goes wrong and the others that return a value if something goes wrong
        // offer(), poll() and peek() are more common

        // ex queue: (queue = line of people)
        Queue<Integer> queue = new ArrayDeque<>();
        System.out.println(queue.offer(10));    // true     -> in queue: 10
        System.out.println(queue.offer(4));     // true     -> in queue: 10  4
        System.out.println(queue.peek());           // 10       -> in queue: 10 4
        System.out.println(queue.poll());           // 10       -> in queue: 4
        System.out.println(queue.poll());           // 4        -> in queue: -
        System.out.println(queue.peek());           // null     -> in queue: -

        // same ex using stack functionality: (stack = stack of plates)
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(10);                         //          -> in stack: 10
        stack.push(4);                          //          -> in stack: 4  10
        System.out.println(stack.peek());           // 4        -> in stack: 4 10
        System.out.println(stack.poll());           // 4        -> in stack: 10
        System.out.println(stack.poll());           // 10       -> in stack: -
        System.out.println(stack.peek());           // null     -> in stack: -

        // LIFO(stack)              - we use: push(), poll(), peek()
        // FIFO(single-ended queue) - we use: offer(), poll(), peek()
        // LinkedList works the same way as ArrayDeque - but ArrayDeque is more efficient
    }
}
