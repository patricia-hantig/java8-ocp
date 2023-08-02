package com.patri.java.ocp._1_advanced_class_design._4_annotating_overridden_methods;

public class HashCodeMethodExamples {
    public static void main(String[] args) {
        // when you override equals() - is expected to override hashcode()
        // hash code = is a number that puts instances of a class into a finite number of categories
        // ex: consider having a deck of cards - if we want to find a specific card the easiest way is
        //      to have divided all the 52 cards in 12 piles cards of aces, ones, twos and so on
        //      if you want to get the five of hearths -> you just have to look into the pile of fives
        //      instead of searching through all 52 cards
    }

}

// ex: deck of cards - Cards are equal if they have the same rank and suit
class Card {
    private String rank;
    private String suit;

    public Card(String r, String s) {
        if (r == null || s == null)
            throw new IllegalArgumentException();
        rank = r;
        suit = s;
    }

    public boolean equals(Object object) {
        if ( !(object instanceof Card))
            return false;
        Card card = (Card) object;
        return rank.equals(card.rank) && suit.equals(card.suit);
    }

    public int hashCode() {
        return  rank.hashCode();    // it asks the rank for its hash code and uses that
    }
}

// The hash code is just a number
// All of the instance variables don’t need to be used in a hashCode() method
// It is common not to include boolean and char variables in the hash code

// Rules for hashCode() methods:
// 1. Within the same program, the result of hashCode() must not change -> do not include variables
//      that change in figuring out the hash code ( ex: in our Hippo ex - including the name is ok, but the weight is not - hippos can change weight regularly)
// 2. if a.equals(b) == true -> hashCode(a) should be equal with hashCode(b)
// 3. if a.equals(b) == false -> hashCode(a) could be equal or not with hashCode(b)

// ex:
class Lion2 {
    private int idNumber;
    private int age;
    private String name;

    public Lion2(int idNumber, int age, String name) {
        this.idNumber = idNumber;
        this.age = age;
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {              // uses only idNumber in the equals()
        if (!(object instanceof Lion2)) return false;
        Lion2 otherLion = (Lion2) object;
        return this.idNumber == otherLion.idNumber;
    }

    // which of the following methods are legal hashCode() methods?
    public int hashCode() { return idNumber; }              // the right hashCode() method
    // public int hashCode() {  return  6; }                // legal, but isn't efficient
    public long hashcode() { return idNumber; }             // not an overridden hashCode() - lowercase c + type is wrong (it's long, not int)
    //public int hashCode() { return idNumber * 7 + age}    // not legal - it uses more variables than equal()
}

// ■ The easy way to write hashCode() methods: (real world scenario)
// use the open source library: Apache Commons Lang (http://commons.apache.org/proper/commons-lang/)

// It is easier to code your own - just pick the key fields that identify your object (and
// don’t change during the program) and combine them

// ex:
// public int hashCode() {
//      return keyFiled + 7 * otherKeyField.hashCode();
// }

// It is common to multiply by a prime number when combining multiple fields in the hash code
// this makes the code more unique -> helps distributing objects into buckets
