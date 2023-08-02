package com.patri.java.ocp._6_exceptions_and_assertions._3_try_with_resources.autocloseable;

public class Examples {
}

class ExampleOne implements AutoCloseable {         //this is the best implementation
    @Override
    public void close() throws IllegalStateException {
        throw new IllegalStateException("Cage door does not close");
    }
}

class ExampleTwo implements AutoCloseable {         // it throws Exception rather than a more specific subclass
    @Override
    public void close() throws Exception {
        throw new Exception("Cage door does not close");
    }
}

class ExampleThree implements AutoCloseable {       // it has a side effect (side effect = changes the state of a variable)
                                                    // side effects are not recommended
    static int COUNT = 0;

    @Override
    public void close()  {
        COUNT++;
    }
}