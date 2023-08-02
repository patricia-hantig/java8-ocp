package com.patri.java.ocp._2_design_patterns_and_principles._5_design_patterns;

public class SingletonPattern {
    // ■■■ Problem: - How to create an object in memory only once in an application and share it in multiple classes?

    // ■■■ Motivation: - sometimes we want only one instance of a particular type of object in memory
    // ex: manage the amount of hay available - HayManager -> we would have to pass the same shared HayManager object to every class - difficult to manage
    //      so by creating a singleton HayManager object - we centralize the data and remove the need to pass it around the application

    // ■■■ Solution: - create only one instance of an object in memory within an application - sharable by all classes and threads within the application
    //              the globally available object created by the singleton pattern - singleton
}


// ■■ Solution 1:
// the singleton object is instantiated directly in the definition of the instance reference

// A class is singleton if:
// 1. all constructors are private
// 2. has only one instance variable (instance of the class): 'private static final' - which is instantiated only once -> when is declared
// 3. has a method which returns the instance: getInstance() { return instance; } -> which returns the reference to the singleton object
// 4. all the other methods are declared as 'synchronized' - it prevents 2 processes from running the same method at the exact same time

// HayStorage is a singleton object
class HayStorage {
    private int quantity = 0;
    private HayStorage() {}         // private constructor

    private static final HayStorage instance = new HayStorage();    // instance of the class

    public static HayStorage getInstance() {    // getInstance() method - which returns the reference to the singleton object
        return instance;
    }

    public synchronized  void addHay(int amount) {      // all other methods are synchronized
        quantity += amount;
    }

    public synchronized boolean removeHay(int amount) {     // all other methods are synchronized
        if (quantity < amount) return false;
        quantity -= amount;
        return true;
    }

    public synchronized int getHayQuantity() {          // all other methods are synchronized
        return quantity;
    }
}
// Use of the singleton object
class LlamaTrainer {
    public boolean feedLlamas(int numberOfLlamas) {
        int amountNeeded = 5 * numberOfLlamas;
        HayStorage hayStorage = HayStorage.getInstance();
        if (hayStorage.getHayQuantity() < amountNeeded) {
            hayStorage.addHay(amountNeeded + 10);
        }
        boolean fed = hayStorage.removeHay(amountNeeded);
        if (fed)
            System.out.println("Llamas have been fed");
        return fed;
    }
}


// ■■ Solution 2:
// the singleton object is instantiated using a static block when the class is loaded

// A class is singleton if:
// 1. all constructors are private
// 2. has only one instance variable (instance of the class): 'private static final'
// 3. has a static block - which instantiate the instance only once
// 4. has a static method which returns the instance: static getInstance() { return instance; } -> which returns the reference to the singleton object
// 5. all the other methods are declared as 'synchronized' - it prevents 2 processes from running the same method at the exact same time
class StaffRegister {
    private StaffRegister() {}  // private constructor

    private static final StaffRegister instance;    // instance of the class
    static {                                        // static block where instance of the class is initialized
        instance = new StaffRegister();
    }

    private static StaffRegister getInstance() {    // getInstance() method - which returns the reference to the singleton object
        return instance;
    }

    // other methods
}


// ! Both solutions from above instantiate the singleton at the time the class is loaded


// ■■ Solution 3:
// applying lazy instantiation - delay the creation of a singleton object until the first time the getInstance() method is called

// A class is singleton if:
// 1. all constructors are private
// 2. has only one instance variable (instance of the class): 'private static'
// 3. has a static method which instantiates and returns the instance: static getInstance()
//    lazy instantiation = the singleton object is created when the method getInstance() is called not when the class is loaded
// 4. all the other methods are declared as 'synchronized' - it prevents 2 processes from running the same method at the exact same time

class VisitorTicketTracker {
    private VisitorTicketTracker() {}       // private constructor

    private static VisitorTicketTracker instance;   // instance of the class

    public static VisitorTicketTracker getInstance() {   // getInstance() method - which returns the reference to the singleton object
        if (instance == null) {
            instance = new VisitorTicketTracker();  // NOT THREAD-SAFE!
            // lazy instantiation = creating the object the first time is requested
            // Lazy instantiation reduces memory usage and improves performance when an application starts up
        }
        return instance;
    }

    // other methods
}
// Problem: the implementation from above is not thread-safe => 2 threads could call getInstance() at the same time - so we would have 2 objects
// thread safety = the property of an object that guarantees safe execution by multiple threads at the same time

// Solution: use synchronization
class VisitorTicketTracker2 {
    private VisitorTicketTracker2() {}       // private constructor

    private static VisitorTicketTracker2 instance;   // instance of the class

    public static synchronized VisitorTicketTracker2 getInstance() {   // getInstance() method - which returns the reference to the singleton object
        if (instance == null) {
            instance = new VisitorTicketTracker2();  // it's THREAD-SAFE
        }
        return instance;
    }

    // other methods
}
// Problem: the synchronized implementation of getInstance() - prevents multiple singleton objects from being created, but
//          every time the method is called - the call requires synchronization => can impact performance!
//          synchronization is only needed the first time that the object is created
// Solution: use double-checked locking
class VisitorTicketTracker3 {
    private VisitorTicketTracker3() {}       // private constructor

    private static volatile VisitorTicketTracker3 instance;   // instance of the class
    // volatile - prevents the case in which the compiler tries to optimize the code and the object is accessed before it is finished being constructed

    public static VisitorTicketTracker3 getInstance() {   // getInstance() method - which returns the reference to the singleton object
        if (instance == null) {
            synchronized (VisitorTicketTracker3.class) { // we need synchronization only the first time when the object is created
                if (instance == null) {
                    instance = new VisitorTicketTracker3();
                }
            }
        }
        return instance;
    }

    // other methods
}
// The last implementation is the best one because the synchronization is made only when the object does not exist yet


