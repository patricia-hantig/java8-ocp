package com.patri.java.ocp._4_functional_programming._5_stream_primitives;

import java.util.IntSummaryStatistics;
import java.util.OptionalInt;
import java.util.stream.IntStream;

// we use summaryStatistics when we want to do more than 1 terminal operation on a stream
public class SummaryStatistics {

    // ex: write a method that returns maximum value of a stream of int and if is empty throws exception
    private static int method(IntStream stream) {
        OptionalInt optional = stream.max();
        return optional.orElseThrow(RuntimeException::new);
    }

    // ex: but if we want a method that takes an IntStream and returns a range?
    // range = maxValue - minValue
    // min() and max() = are both terminal operations => We CANNOT have 2 terminal operations on the same stream!!!
    // Solution: we use summaryStatistics -> Statistic is just a number that was calculated from data.
    private static int rangeMethod(IntStream stream) {
        IntSummaryStatistics statistics = stream.summaryStatistics();
        if (statistics.getCount() == 0) throw new RuntimeException();
        return statistics.getMax() - statistics.getMin();
    }

    public static void main(String[] args) {
        IntStream stream = IntStream.of(30, 12, 18, 44, 9);
        System.out.println(method(stream)); // 44
        IntStream stream1 = IntStream.of(30, 12, 18, 44, 9);
        System.out.println(rangeMethod(stream1)); // 44 - 9 = 35
    }
}
