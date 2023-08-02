package com.patri.java.ocp._7_concurrency._5_parallel_streams;

import java.util.Arrays;

// by default all streams are ordered streams
public class CreatingUnorderedStreams {
    public static void main(String[] args) {
        // .unordered() - this method does not reorder elements!!!!!
        // it tells JVM that if there is an order-base operation then the order can be ignored
        // unordered() - has effects only on parallel streams
        Arrays.asList(1, 2, 3, 4, 5, 6).stream().unordered().limit(4).forEach(System.out::print);
        System.out.println();
        Arrays.asList(1, 2, 3, 4, 5, 6).stream().unordered().parallel().limit(4).forEach(System.out::print);
    }
}
