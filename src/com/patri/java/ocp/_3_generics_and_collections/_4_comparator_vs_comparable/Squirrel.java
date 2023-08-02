package com.patri.java.ocp._3_generics_and_collections._4_comparator_vs_comparable;

import java.util.Comparator;

// ex: for Comparator -> it's used for comparing multiple fields
public class Squirrel {
    private int weight;
    private String species;

    // we assume that species name will never be null
    public Squirrel(String theSpecies) {
        if (theSpecies == null) throw new IllegalArgumentException();
        species = theSpecies;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getSpecies() {
        return species;
    }
}

// we want to write a Comparator to sort by species name, if 2 squirrels are from the same species,
// we want to sort the one that weighs the least first
class MultiFieldComparator implements Comparator<Squirrel> {

    @Override
    public int compare(Squirrel s1, Squirrel s2) {
        int result = s1.getSpecies().compareTo(s2.getSpecies());
        if (result != 0) return result;         // It checks 'species' field -> if they don’t match, we are finished sorting
        return s1.getWeight()-s2.getWeight();   // If they do match -> it looks to the 'weight' field
    }
}

// with Java 8 the above method can be rewritten:
class ChainingComparator implements Comparator<Squirrel> {

    @Override
    public int compare(Squirrel s1, Squirrel s2) {
        Comparator<Squirrel> comparator = Comparator.comparing(s -> s.getSpecies()); // get the species value out of the squirrel and pass it to the method
        comparator = comparator.thenComparing(s -> s.getWeight());  // first we sort by species, and then we sort by weight

        // the 2 lambdas could be written using method chaining:
        Comparator<Squirrel> squirrelComparator = Comparator.comparing((Squirrel s) -> s.getSpecies()).thenComparing(s -> s.getWeight());

        return comparator.compare(s1, s2);
    }
}
