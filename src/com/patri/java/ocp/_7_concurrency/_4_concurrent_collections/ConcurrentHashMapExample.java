package com.patri.java.ocp._7_concurrency._4_concurrent_collections;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapExample {
    public static void main(String[] args) {
        Map<String, Integer> map = new ConcurrentHashMap<String, Integer>();
        map.put("zebra", 52);
        map.put("elephant", 10);
        System.out.println(map.get("elephant"));
        System.out.println(map.get("zebra"));
        System.out.println(map.get("penguin")); // null
    }
}
