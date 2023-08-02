package com.patri.java.ocp._1_advanced_class_design._1_review_OCA;

import java.util.ArrayList;
import java.util.List;
import static java.util.Collections.sort;

public class Imports {
    // import java.lang.* - is implicit added to all clasees
}

// ex: Which are the imports needed here?
class ListHelper {
    public List<String> copyAndSortList(List <String> original) {
        List <String> list = new ArrayList<String>(original);
        sort(list);
        return list;
    }
}

// sort() is a static method on Collections - we would need:
// import static java.util.Collections.sort; or import static java.util.Collections.*;

// for List and ArrayList - we would need:
// import java.util.*;

//The other option is to list them out:
//import java.util.List;
//import java.util.ArrayList;