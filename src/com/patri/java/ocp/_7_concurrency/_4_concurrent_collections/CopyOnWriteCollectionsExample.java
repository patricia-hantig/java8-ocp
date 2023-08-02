package com.patri.java.ocp._7_concurrency._4_concurrent_collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteCollectionsExample {
    public static void main(String[] args) {
        List<Integer> list = new CopyOnWriteArrayList<>(Arrays.asList(4, 3, 52));
        System.out.println("elements of original list");
        for (Integer item : list) {
            System.out.print(item + " ");
            list.add(9);
        }
        System.out.println();
        System.out.println("size of new list: " + list.size());
        System.out.println("elements of new list");
        for (Integer item : list) {
            System.out.print(item + " ");
        }

        // if we had
        // List<Integer> list = new ArrayList<>(Arrays.asList(4, 3, 52));
        // we get: Exception in thread "main" java.util.ConcurrentModificationException
    }
}
