package com.patri.java.ocp._7_concurrency._4_concurrent_collections;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// example for: memory consistent error = appear when 2 threads has inconsistent views about piece of data (that should be the same)
public class MemoryConsistentErrors {

    public static void main(String[] args) {

        // thread modifies the same (non-concurrent) collection 2 times
        /*Map<String, Object> foodData1 = new HashMap<String, Object>();
        foodData1.put("penguin", 1);
        foodData1.put("dolphin", 2);
        for (String key: foodData1.keySet()) {
            foodData1.remove(key);  // throws ConcurrentModificationException because iterator keySet() is not modified after
                                    // the remove of first element from the map
        }   */

        // if we work with concurrent collection => we don't get exception (iterator keySet() is MODIFIED when the object is
        // removed from Map
        Map<String, Object> foodData2 = new ConcurrentHashMap<String, Object>();
        foodData2.put("penguin", 1);
        foodData2.put("dolphin", 2);
        for (String key: foodData2.keySet()) {
            foodData2.remove(key);
        }

        // when to use/not use concurrent collections:
        // TO USE: when we have more threads that modifies the objects of a collection that doesn't have synchronized methods ( even
        //          if there are no concurrency problems)
        // TO NOT USE: when threads access the same immutable and read-only collection

        // GOOD PRACTICE: instantiate a concurrent collection, but pass it around using a non-concurrent interface whenever possible
    }
}
