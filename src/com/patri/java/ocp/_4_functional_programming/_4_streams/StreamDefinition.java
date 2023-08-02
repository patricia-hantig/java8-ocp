package com.patri.java.ocp._4_functional_programming._4_streams;

public class StreamDefinition {
    // Stream = sequence of data
    // Stream pipeline = the operations that run on a stream to produce a result
    // Stream pipeline - is like an assembly line in a factory

    // finite streams - have a limit & we also have infinite streams
    // an important feature of an assembly line/stream is that each person touches each element to do their operation and then
    // that piece of data is gone => compared to lists and queues where you can access it at any time
    //with streams the data isn’t generated up front — it is created when needed

    // Stream pipeline: Source -> {Intermediate operations} -> Terminal operation
    // ■ Source - Where the stream comes from
    // ■ Intermediate operations - Transforms the stream into another one
    //                           - There can be 0 or more in a stream
    //                           - streams use lazy evaluation => the intermediate operations do not run until the terminal operation runs
    // ■ Terminal operation - produces the result and the stream is no longer available after the terminal operation completes

    // intermediate vs terminal operations:
    // Scenario                                   for intermediate op     |   for terminal op
    // ------------------------------------------------------------------------------------------
    // Required part of a useful pipeline?              No                          Yes
    // Can exist multiple times in a pipeline?          Yes                         No
    // Return type is a stream type?                    Yes                         No
    // Executed upon method call?                       No                          Yes
    // Stream valid after call?                         Yes                         No


}
