package com.patri.java.ocp._7_concurrency._7_threading_problems;

// threading problem = it appears in multithreading applications when 2 or more threads interact in an undesirable way
// What happens in an application if all threads are waiting? -> the waiting = the user doesn't know what happened
// solution: Concurrency API reduces thread pb, but doesn't eliminate them

// Liveness = the ability of an application to execute on a timely manner
// liveness problems - the application becomes unresponsive or stuck
// 3 types of liveness problems: deadlock, starvation and livelock

public class LivenessExample {

    // DEADLOCK = appears when 2 or more threads are blocked forever, each waiting for the other

    // ex: Zoo has 2 foxes: Foxy and Tails
    // Foxy - eats first and then drinks water
    // Tails - drinks water first and then eats
    // both finish their meals if they have access to food and water
    // water is on one side and food on the other side - it takes them 100 milliseconds to run from one side to the other
    // What happens if Foxy gets the food first and Tails gets the water first?

    // example Fox.java

    // preventing deadlocks:
    // How do you fix a deadlock once it has occurred? The answer is that you can't in most situations
    // strategies to prevent deadlocks:
    // 1. all threads to order their resource requests (ex: if both foxes have a rule that they need to obtain food before water,
    // then the previous deadlock scenario will not happen again. Once one of the foxes obtained food, the second fox would wait,
    // leaving the water resource available)
    // 2. advanced techniques that try to detect and resolve a deadlock in real time - difficult to implement


    // STARVATION = appears when one thread can't access a shared resource/lock => the thread is active, but it can't manage to access
    //              the resource because other threads are constantly taking the resource that he want to access

    // example: for example we have a group of hungry foxes and very competitive. Each time Foxy stands up to go get food, one of the
    // foxes sees her and rushes to eat before her. Foxy can't access food => Foxy experience STARVATION!


    // LIVELOCK = occurs when two or more threads are conceptually blocked forever, although they are each still active
    //            and trying to complete their task
    // Livelock - special case of resource starvation in which 2 or more threads actively try to acquire a set of locks,
    // are unable to do so, and restart part of the process
    // Livelock - is usually the result of two threads trying to resolve a deadlock

    // example: imagine that Foxy and Tails are both holding their food and water resources, respectively.
    // They each realize that they cannot finish their meal in this state, so they both let go of their food and water,
    // run to the opposite side of the environment, and pick up the other resource. Now Foxy has the water, Tails has the food,
    // and neither is able to finish their meal!
    // If Foxy and Tails continue this process forever => LIVELOCK
    // the lock and unlock process is cyclical and the 2 foxes are conceptually deadlocked

    // Threads in a LIVELOCK state - appear to be active and able to respond to requests, even when they are in fact stuck
    // in an endless cycle!
}
