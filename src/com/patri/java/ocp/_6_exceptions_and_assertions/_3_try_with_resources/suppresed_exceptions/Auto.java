package com.patri.java.ocp._6_exceptions_and_assertions._3_try_with_resources.suppresed_exceptions;

// there 2 rules for try-with-resources:
// 1. resources are close after the try clause ends and before any catch and finally
// 2. resources are closed in reverse order from which they were created

public class Auto implements AutoCloseable {

    int num;

    public Auto(int num) {
        this.num = num;
    }

    @Override
    public void close() throws Exception {
        System.out.println("Close : " + num);
    }

    public static void main(String[] args) {
        try (Auto auto = new Auto(1);
             Auto auto2 = new Auto(2)) {
            throw new RuntimeException();
        } catch (Exception e) {
            System.out.println("ex");
        } finally {
            System.out.println("finally");
        }
    }
}
