package com.patri.java.ocp._6_exceptions_and_assertions._3_try_with_resources.autocloseable;

// AutoCloseable added in Java 7
// before Java 7 there was Closeable - more restricted ('close()' throws IOException, required implementations to be idempotent)
// idempotent method = method can be called by multiple times without side effects or without changed behavior
public class TurkeyCage implements AutoCloseable {

    //method overridden is: 'public void close() throws Exception'
    @Override
    public void close() {
        System.out.println("Close gate");
    }

    public static void main(String[] args) {
        try (TurkeyCage turkeyCage = new TurkeyCage()) {    // a class can be declared in try-with-resources
                                                            // only if it implements AutoCloseable
            System.out.println("put turkeys in");
        }
    }
}
