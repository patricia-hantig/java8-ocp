package com.patri.java.ocp._7_concurrency._4_concurrent_collections;

import java.util.*;

// synchronized collection = synchronized version of a non-concurrent collection

// synchronized collection methods: synchronizedCollection(Collection<T> c), synchronizedList(List<T> l), synchronizedMap(Map<K, V> m),
// synchronizedNavigableMap(NavigableMap<K, V> m), synchronizedSet(Set<T> s), synchronizedSortedSet(SortedSet<T> s),
// synchronizedSortedMap(SortedMap<K, V> m)

// we use them, if at the moment of creation we don't know if we need synchronization (if we have a non-concurrent collection and
// use multi-threading
public class SynchronizedCollections {
    public static void main(String[] args) {
        List<Integer> list = Collections.synchronizedList(new ArrayList<>(Arrays.asList(4, 3, 52)));
        synchronized (list) {
            for (int data : list) {
                System.out.print(data + " ");
            }
        }

        // just like concurrent collections -> synchronized collections throw ConcurrentModificationException
        Map<String, Object> foodData = new HashMap<String, Object>();
        foodData.put("penguin", 1);
        foodData.put("flamingo", 2);

        Map<String, Object> synchronizedFoodData = Collections.synchronizedMap(foodData);
        for (String key: synchronizedFoodData.keySet()) {
            synchronizedFoodData.remove(key); // throws exception if they are modified within an iterator by a single thread
        }
    }
}
