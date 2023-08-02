package com.patri.java.ocp._7_concurrency._4_concurrent_collections;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// example for: introduction concurrent collections
// we use concurrent collections because they can have improvements for performance, because we lost the unnecessary synchronizing
public class ZooManager {

    // implementation with synchronized
    private Map<String, Object> foodData1 = new HashMap<String, Object>();
    public synchronized void put1(String key, String value) {
        foodData1.put(key, value);
    }
    public synchronized Object get1(String key) {
        return foodData1.get(key);
    }

    // same implementation with concurrent collections
    private Map<String, Object> foodData2 = new ConcurrentHashMap<String, Object>();
    public void put2(String key, String value) {
        foodData2.put(key, value);
    }
    public Object get2(String key) {
        return foodData1.get(key);
    }
}
