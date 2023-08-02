package com.patri.java.ocp._4_functional_programming._5_stream_primitives;

import java.util.function.BooleanSupplier;

public class FunctionalInterfacesForPrimitives {
    public static void main(String[] args) {

        // ■■■ Functional Interfaces for "boolean"

        // we have only BooleanSupplier - it has one method: boolean getAsBoolean()
        BooleanSupplier b1 = () -> true;
        BooleanSupplier b2 = () -> Math.random() > .5;
        System.out.println(b1.getAsBoolean());
        System.out.println(b2.getAsBoolean());


        // ■■■ Functional Interfaces for "double", "int" and "long"

        // Common functional interfaces for primitives:
        // ■ for Supplier:
        // Functional Interfaces     |   # Parameters     |  Return Type     |   Single Abstract Method
        // ---------------------------------------------------------------------------------------------
        // DoubleSupplier                   0                   double              getAsDouble()
        // IntSupplier                      0                   int                 getAsInt()
        // LongSupplier                     0                   long                getAsLong()

        // ■ for Consumer:
        // ---------------------------------------------------------------------------------------------
        // DoubleConsumer                   1(double)           void                accept()
        // IntConsumer                      1(int)              void                accept()
        // LongConsumer                     1(long)             void                accept()

        // ■ for BiConsumer - we DON'T HAVE

        // ■ for Predicate:
        // ---------------------------------------------------------------------------------------------
        // DoublePredicate                  1(double)           boolean             test()
        // IntPredicate                     1(int)              boolean             test()
        // LongPredicate                    1(long)             boolean             test()

        // ■ for BiPredicate - we DON'T HAVE

        // ■ for Function:
        // ---------------------------------------------------------------------------------------------
        // DoubleFunction<R>                1(double)           R                   apply()
        // IntFunction<R>                   1(int)              R                   apply()
        // LongFunction<R>                  1(long)             R                   apply()

        // ■ for BiFunction - we DON'T HAVE

        // ■ for UnaryOperator:
        // ---------------------------------------------------------------------------------------------
        // DoubleUnaryOperator              1(double)           double              applyAsDouble()
        // IntUnaryOperator                 1(int)              int                 applyAsInt()
        // LongUnaryOperator                1(long)             long                applyAsLong()

        // ■ for BinaryOperator:
        // ---------------------------------------------------------------------------------------------
        // DoubleBinaryOperator         2(double, double)       double              applyAsDouble()
        // IntUnaryOperator             2(int, int)             int                 applyAsInt()
        // LongUnaryOperator            2(long, long)           long                applyAsLong()

        // Generics are gone from some of the interfaces - the type name tells us what primitive type is involved
        // In other cases (IntFunction) only the return type generic is needed
        // BiConsumer, BiPredicate, and BiFunction - for primitives the functions with two different type parameters just aren’t used often


        // ■■■ Primitive-specific functional interfaces:
        // Functional Interfaces     |   # Parameters     |  Return Type     |   Single Abstract Method
        // ---------------------------------------------------------------------------------------------
        // ToDoubleFunction<T>              1(T)                double              applyAsDouble()
        // ToIntFunction<T>                 1(T)                int                 applyAsInt()
        // ToLongFunction<T>                1(T)                long                applyAsLong()
        // ---------------------------------------------------------------------------------------------
        // ToDoubleBiFunction<T, U>         2(T, U)             double              applyAsDouble()
        // ToIntBiFunction<T, U>            2(T, U)             int                 applyAsInt()
        // ToLongBiFunction<T, U>           2(T, U)             long                applyAsLong()
        // ---------------------------------------------------------------------------------------------
        // DoubleToIntFunction<T>           1(double)           int                 applyAsInt()
        // DoubleToLongFunction<T>          1(double)           long                applyAsLong()
        // IntToDoubleFunction<T>           1(int)              double              applyAsDouble()
        // IntToLongFunction<T>             1(int)              long                applyAsLong()
        // LongToDoubleFFunction<T>         1(long)             double              applyAsDouble()
        // LongToIntFunction<T>             1(long)             int                 applyAsInt()
        // ---------------------------------------------------------------------------------------------
        // ObjDoubleConsumer<T>             2(T, double)        void                accept()
        // ObjIntConsumer<T>                2(T, int)           void                accept()
        // ObjLongConsumer<T>               2(T, long)          void                accept()


        // ex Which functional interface would you use to fill in the blank to make the following code compile?
        // double d = 1.0;
        // _________ f1 = x -> 1;
        // f1.applyAsInt(d);

        // solution: the functional interface that we are looking for:
        // returns int
        // method is applyAsInt() - and takes 1 double param => DoubleToIntFunction
    }
}
