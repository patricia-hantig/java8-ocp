package com.patri.java.ocp._3_generics_and_collections._3_lists_sets_maps_queues;

public class ComparingCollectionTypes {
    public static void main(String[] args) {

        // ■ Java Collections Framework types
        //  Type    |   Can have duplicates?    | Elements ordered?     |   Has keys and value? |   Must add/remove in specific order?
        // ----------------------------------------------------------------------------------------------------------------------------
        //  List    |           YES             |   YES (by index)      |           NO          |                   NO
        //  Set     |           NO              |           NO          |           NO          |                   NO
        //  Queue   |           YES             | YES ( queue, stack)   |           NO          |                   YES
        //  Map     |   NO - keys, YES - values |           NO          |           YES         |                   NO
        // ----------------------------------------------------------------------------------------------------------------------------

        // ■ Collection attributes
        //      Type    |   Java Collections Framework interface    |   Sorted      | Calls hashCode()? |    Calls compareTo()?
        // ------------------------------------------------------------------------------------------------------------------------
        //  ArrayList   |                   List                    |       NO      |       NO          |           NO
        //  ArrayDeque  |                   Queue                   |       NO      |       NO          |           NO
        //  HashMap     |                   Map                     |       NO      |       YES         |           NO
        //  HashSet     |                   Set                     |       NO      |       YES         |           NO
        //  Hashtable   |                   Map                     |       NO      |       YES         |           NO
        //  LinkedList  |                   List, Queue             |       NO      |       NO          |           NO
        //  Stack       |                   List                    |       NO      |       NO          |           NO
        //  TreeMap     |                   Map                     |       YES     |       NO          |           YES
        //  TreeSet     |                   Set                     |       YES     |       NO          |           YES
        //  Vector      |                   List                    |       NO      |       NO          |           NO
        // ------------------------------------------------------------------------------------------------------------------------


        // ■■ which Collections do not allow 'nulls' and why?
        // which? - data structures that involve sorting do not allow nulls: TreeSet, TreeMap
        // why? - we can't compare a null and a String - they are different things -> we can't say '5' < 'Puppy' => we can't say 'null' < 'Puppy'
        // this means TreeSet cannot contain null elements
        // TreeMap cannot contain null keys, null values are ok

        // which? - ArrayDeque
        // why? - because we have methods like poll() which returns null to indicate that the collection is empty

        // which? - Hashtable
        // why? - there isn’t really a good reason for this one. It’s old and written that way
        // Hashtable does not allow null keys or values

        // Conclusion:
        // All data structures allow nulls except:
        // ■ TreeMap    - no null keys
        // ■ Hashtable  - no null keys or values
        // ■ TreeSet    - no null elements
        // ■ ArrayDeque - no null elements

        // ■■ Choosing the right collection type:
        // --------------------------------------------------------------------------------------------------------------------------------------------------------------
        // Which class do you choose when you want _______      |       Answer      |           Reason
        // --------------------------------------------------------------------------------------------------------------------------------------------------------------
        // to pick the top zoo map off a stack of maps          |   ArrayDeque      |   you need a LIFO data structure => you need a stack
        //                                                      |                   |   which is a type of Queue (Stack would also be ok, but it's old code)
        // --------------------------------------------------------------------------------------------------------------------------------------------------------------
        // to sell tickets to people in the order in which they |   LinkedList      |   you need a FIFO data structure => you need a queue
        // appear in line and tell them their position in line  |                   |   you also need indexes => LinkedList is the solution because it
        //                                                      |                   |   implements both List and Queue (indexes and FIFO)
        // --------------------------------------------------------------------------------------------------------------------------------------------------------------
        // to write down the first names of all the elephants   |   ArrayList       |   since there could be duplicates => you need a list and not a set
        // so that you can tell them to your friends's 3 year   |                   |   you will be accessing the list more often than updating it => better
        // old every time she asks ( the elephants do not have  |                   |   use an ArrayList, rather than a LinkedList
        // unique first names)                                  |                   |   Vector and Stack aren't used in new code
        // --------------------------------------------------------------------------------------------------------------------------------------------------------------
        // to list the unique animals that you want to see at   |       HashSet     |   "unique" = Set => since there is no requirement to have a sorted order or to
        // the zoo today                                        |                   |   remember the insertion order -> you have to use the most efficient set: HashSet
        // --------------------------------------------------------------------------------------------------------------------------------------------------------------
        // to list the unique animals that you want to see at   |      TreeSet      |   "unique" = Set and you need to sort -> you won't choose a HashSet => TreeSet
        // the zoo today in alphabetical order                  |                   |
        // --------------------------------------------------------------------------------------------------------------------------------------------------------------
        // to look up animals based on a unique identifier      |       HashMap     |   Looking up by key = map => since you have no ordering or sorting requirements
        // --------------------------------------------------------------------------------------------------------------------------------------------------------------

    }
}
