package com.patri.java.ocp._3_generics_and_collections._6_new_in_Java8;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

// in Java 8 were added new methods on Map interface
public class NewJava8MapAPIs {
    public static void main(String[] args) {
        // the new methods are: put(), putIfAbsent(), merge(), computeIfPresent(), computeIfAbsent()

        // ■■■ methods for update a value in a Map for a specific key: put() and putIfAbsent()
        // ■ update value even if is initialized or null - put()
        // - method signature: V put(K key, V value)
        // ex:
        Map<String, String> favorites = new HashMap<>();
        favorites.put("Jenny", "Bus Tour");
        System.out.println(favorites);
        favorites.put("Jenny", "Tram");
        System.out.println(favorites);
        System.out.println();

        // ■ update value only if is null - putIfAbsent()
        // - method signature: V putIfAbsent(K key, V value)
        Map<String, String> favorites2 = new HashMap<>();
        favorites2.put("Jenny", "Bus Tour");
        favorites2.put("Tom", null);
        System.out.println(favorites2);
        favorites2.putIfAbsent("Jenny", "Tram");    // Jenny’s value is not updated because was already present
        favorites2.putIfAbsent("Sam", "Tram");
        favorites2.putIfAbsent("Tom", "Tram");
        System.out.println(favorites2);
        System.out.println();


        // ■■■ method merge() - adds logic
        // - method signature: V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> mappingFunction)

        // ■ if the mappingFunction from merge() returns VALUE
        // ex:
        // mappingFunction is a BiFunction - with value1, value2 as inputs + output
        BiFunction<String, String, String> mapper = (v1, v2) -> v1.length() > v2.length() ? v1 : v2;
        Map<String, String> fav = new HashMap<>();
        fav.put("Jenny", "Bus Tour");
        fav.put("Tom", "Tram");
        System.out.println(fav);
        String jenny = fav.merge("Jenny", "Skyride", mapper); // the mapping function sees that "Bus Tour" > "Skyride" -> so it leaves the value "Bus Tour"
        System.out.println(jenny);
        String tom = fav.merge("Tom", "Skyride", mapper); // the mapping function sees that "Tram" < "Skyride" -> the map is updated
        System.out.println(tom);
        System.out.println(fav);
        // if we have null value in a map for a key -> it doesn't call the BiFunction because mapping requires 2 values (null is not a value
        // - if the mapping would be called - we would had a NulPointerException) -> it simply uses the new value
        fav.put("Ana", null);
        System.out.println(fav);
        String ana = fav.merge("Ana", "Skyride", mapper);
        System.out.println(ana);
        System.out.println(fav);
        // if the key for which we do the merge does not exist in the map -> the mapping function is not called -> it simply adds the new key with value in the map
        String sam = fav.merge("Sam", "Skyride", mapper);
        System.out.println(sam);
        System.out.println(fav);
        System.out.println();

        // ■ if the mappingFunction from merge() returns NULL -> the key for which the mapping takes place is removed from the Map !!!
        // ex:
        // mapping function is a BiFunction - with value1, value2 as inputs + output = NULL
        BiFunction<String, String, String> mapper2 = (v1, v2) -> null;
        Map<String, String> fav2 = new HashMap<>();
        fav2.put("Jenny", "Bus Tour");
        fav2.put("Tom", "Bus Tour");
        System.out.println(fav2);
        fav2.merge("Jenny", "Skyride", mapper2);    // we did merge on "Jenny" and the mapping function returned null -> element was removed from the map
        System.out.println(fav2);                           // "Tom" was left alone since there was no merge on it
        fav2.merge("Sam", "Skyride", mapper2);  // "Sam" was added to the map since that key was not in the original map
        System.out.println(fav2);
        System.out.println();


        // ■■■ methods computeIfPresent() and computeIfAbsent() - both return the value corresponding to that key or null
        // if that doesn't apply (if the key for which we do the compute is not in the Map)

        // ■ computeIfPresent() - calls the BiFunction if the requested key is found in Map
        // - method signature: V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction)
        // we use a BiFunction - because we have key and value as input parameters
        // ex:
        Map<String, Integer> counts = new HashMap<>();
        counts.put("Jenny", 1);
        System.out.println(counts);
        BiFunction<String, Integer, Integer> mapper3 = (k, v) -> v + 1;
        Integer jenny1 = counts.computeIfPresent("Jenny", mapper3); // 'Jenny' has its value updated because it is in the map
        System.out.println(jenny1);
        Integer sam1 = counts.computeIfPresent("Sam", mapper3);     // 'Sam' is not in the map
        System.out.println(sam1);
        System.out.println(counts);
        System.out.println();

        // ■ computeIfAbsent() - calls the Function if the requested key is not found in Map or null
        // - method signature: V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction)
        // we use a Function - because we have only key as input parameter
        // ex:
        Map<String, Integer> counters = new HashMap<>();
        counters.put("Jenny", 15);
        counters.put("Tom", null);
        System.out.println(counters);
        Function<String, Integer> mapper4 = (k) -> 1;
        Integer jenny2 = counters.computeIfAbsent("Jenny", mapper4);    // 'Jenny' doesn't change because its value is in the map
        System.out.println(jenny2);
        Integer sam2 = counters.computeIfAbsent("Sam", mapper4);        // 'Sam' was not in the map so it is added to the map
        System.out.println(sam2);
        Integer tom2 = counters.computeIfAbsent("Tom", mapper4);        // 'Tom' was updated because its value was 'null'
        System.out.println(tom2);
        System.out.println(counters);
        System.out.println();

        // ■ for computeIfPresent() - if the mapping function returns null -> the key is removed from the Map
        // ex:
        Map<String, Integer> map = new HashMap<>();
        map.put("Jenny", 1);
        System.out.println(map);
        map.computeIfPresent("Jenny", (k, v) -> null);
        System.out.println(map);

        // ■ for computeIfAbsent() - if the mapping function returns null -> the key is not added to the Map
        // ex:
        map.computeIfAbsent("Sam", k -> null);
        System.out.println(map);
        map.put("Tom", null);
        System.out.println(map);
        map.computeIfAbsent("Tom", k -> null);
        System.out.println(map);


        // ■■■ Merge() and compute() methods:
        //          Scenario            |           merge()             |   computeIfAbsent()   |           computeIfPresent()
        // _______________________________________________________________________________________________________________________
        // Key already in Map           |       Result of function      |       Nothing         |   Result of function
        // -----------------------------------------------------------------------------------------------------------------------
        // Key not already in Map       |       Add new key to map      |   Result of function  |       Nothing
        // -----------------------------------------------------------------------------------------------------------------------
        // Functional Interface used    |           BiFunction          |       Function        |       BiFunction
        //                              | (takes existing value and new | ( takes key and       | (takes key and existing value;
        //                              | value; returns new value)     |   returns new value)  | returns new value)
        // -----------------------------------------------------------------------------------------------------------------------


        // ■■■ Merge() and compute() methods when null is involved:
        //      Key         | Mapping function returns  |               merge()                 |                   computeIfAbsent()                   |           computeIfPresent()
        // -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //  Null in Map     |           null            | remove key from map                   |                           -                           |               -
        //  Null in Map     |           not null        | set key to mapping function result    | add key in map with value = mapping function result   |               -
        //  Not-Null in Map |           null            | remove key from map                   |                           -                           | remove key from map
        //  Not-Null in Map |           not null        | set key to mapping function result    |                           -                           | set key to mapping function result
        //  Key not in Map  |           null            | add key to map                        |                           -                           |               -
        //  Key not in Map  |           not null        | add key to map                        | add key in map with value = mapping function result   |               -
        // -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    }
}
