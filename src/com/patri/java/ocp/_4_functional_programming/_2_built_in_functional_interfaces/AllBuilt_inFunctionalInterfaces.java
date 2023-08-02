package com.patri.java.ocp._4_functional_programming._2_built_in_functional_interfaces;

public class AllBuilt_inFunctionalInterfaces {

    // functional interface = has exactly ONE abstract method
    // all the functional interfaces from below were added in Java 8 and are located in package: java.util.function
    // conventions: T - type param, U - second type param, R - return type
    // these functional interfaces are used within streams

    // Runnable is used in concurrency

    // Common Functional Interfaces:

    // Functional Interface             Param               Return Type                 Abstract method
    //---------------------------------------------------------------------------------------------------
    // Supplier<T>                      0                   T                           get()
    // Consumer<T>                      1 (T)               void                        accept(T t)
    // BiConsumer<T, U>                 2 (T, U)            void                        accept(T t, U u)
    // Predicate<T>                     1 (T)               boolean                     test(T t)
    // BiPredicate<T, U>                2 (T, U)            boolean                     test(T t, U u)
    // Function<T, R>                   1 (T)               R                           apply(T t)
    // BiFunction<T, U, R>              2 (T, U)            R                           apply(T t, U u)
    // UnaryOperator<T>                 1 (T)               T                           apply(T t)
    // BinaryOperator<T>                2 (T, T)            T                           apply(T t)
    // Runnable                         0                   void                        run()
}
