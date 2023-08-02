package com.patri.java.ocp._3_generics_and_collections._4_comparator_vs_comparable;

// example: compareTo() & equals() consistency
public class Product implements Comparable<Product> {

    // if the class implements Comparable - you introduce new business logic for determining equality
    // if 2 objects are equal => compareTo() returns '0' and equals() returns 'true'
    // if you override compareTo() you should also be consistent with equals() => if x.compareTo(y) == 0 then x.equals(y) == true

    // best practice: make your Comparable classes consistent with equals because not all collection classes behave predictably if
    // the compareTo() and equals() methods are not consistent

    // ex: here we have a compareTo() which is not consistent wth equals()

    int id;
    String name;

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Product)) {
            return false;
        }
        Product other = (Product) obj;
        return this.id == other.id;
    }

    @Override
    public int compareTo(Product product) {
        return this.name.compareTo(product.name);
    }

    // Problem: you might be sorting Product objects by name, but names are not unique => the return value
    // of compareTo() might not be 0 when comparing 2 equal Product objects
    // => so this compareTo() is not consistent with equals()

    // Solution: one solution is to use a Comparator to define the sort elsewhere
}
