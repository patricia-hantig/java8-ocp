package com.patri.java.ocp._3_generics_and_collections._3_lists_sets_maps_queues;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class UsingMapInterface {
    public static void main(String[] args) {
        // Map = is a collection in which each value is identified by a key
        // keys cannot be duplicate
        // it doesn't extend Collection interface

        // ■ Map implementations
        // ■■ HashMap = stores the keys in a hash table -> we use hashCode() method of the keys to retrieve their values
        // +++ adding elements and retrieving element by key: O(1)
        // ---  elements are not ordered (for ordered elements - LinkedHashMap)

        // ■■ TreeMap = stores the keys in a sorted tree structure and it's navigable
        // +++ keys are always in sorted order
        // --- adding and checking if a key is present: O(log n)

        // ■■ Hashtable - old code = similar to HashMap, but slower and thread safe

        // ■ Map methods
        // because Map doesn't extend Collection - we have more methods specified on the Map interface
        //              Method                  |              Description
        // --------------------------------------------------------------------------------------------
        //  void clear()                        | removes all keys and values from the map
        //  boolean isEmpty()                   | returns whether the map is empty
        //  int size()                          | returns the number of entries (key/value pairs) in the map
        //  V get(Object key)                   | returns the value mapped by key or null if none is mapped
        //  V put(K key, V value)               | adds/replace key/value pair -> returns value or null
        //  V remove(Object key)                | removes and returns value mapped to key -> returns null if none
        //  boolean containsKey(Object keys)    | returns whether key is in map
        //  boolean containsValue(Object value) | returns whether value is in map
        //  Set<K> keySet()                     | returns set of all keys
        //  Collection<V> values()              | returns Collection of all values

        // example for HashMap:
        Map<String, String> map = new HashMap<>();
        map.put("koala", "bamboo");
        map.put("lion", "meat");
        map.put("giraffe", "leaf");
        String food = map.get("koala");
        System.out.println(food);               // bamboo
        for (String key : map.keySet())
            System.out.print(key + ", ");       // koala, giraffe, lion,
        System.out.println();
        // Java uses the hashCode() of the key to determine the order
        // The order is not sorted order or the order in which we typed the values

        // example for TreeMap:
        Map<String, String> treeMap = new TreeMap<>();
        treeMap.put("koala", "bamboo");
        treeMap.put("lion", "meat");
        treeMap.put("giraffe", "leaf");
        String food2 = treeMap.get("koala");
        System.out.println(food2);              // bamboo
        for (String key : treeMap.keySet())
            System.out.print(key + ", ");       // giraffe, koala, lion,
        // TreeMap sorts the keys as we would expect
        System.out.println();
        for (String value : treeMap.values())
            System.out.print(value + ", ");     // leaf, bamboo, meat,
        // the order of the values would correspond to the order of the keys
        System.out.println();
        // System.out.println(treeMap.contains("lion"));    // DOES NOT COMPILE -> contains() is a method on the Collection interface but not the Map interface.
        System.out.println(treeMap.containsKey("lion"));    // true
        System.out.println(treeMap.containsValue("lion"));  // false
        System.out.println(treeMap.size());                 // 3
    }
}
