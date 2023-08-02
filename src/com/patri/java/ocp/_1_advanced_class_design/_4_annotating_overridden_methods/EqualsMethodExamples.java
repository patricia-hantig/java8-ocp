package com.patri.java.ocp._1_advanced_class_design._4_annotating_overridden_methods;

public class EqualsMethodExamples {
    public static void main(String[] args) {
        // in Java we use '==' to compare primitives and to check if 2 variables refer to the same object
        // we use 'equals()' to check if 2 objects are equivalent
        // equals() returns boolean value
        // equals() signature: public boolean equals(Object obj)

        // ex: here we have 2 classes and we see if they provide custom equals() implementation or not:
        String s1 = new String("lion");
        String s2 = new String("lion");
        System.out.println(s1.equals(s2));          // true - String has a custom equals() method - checks that the values are the same

        StringBuilder sb1 = new StringBuilder("lion");
        StringBuilder sb2 = new StringBuilder("lion");
        System.out.println(sb1.equals(sb2));        // false - StringBuilder doesn't have a custom equals() method - uses the one from Object (which only checks if 2 objects being referred to are the same)

        // Rules for equals() methods:
        // 1. reflexive: x.equals(x) -> always true
        // 2. symmetric: if x.equals(y) = true -> y.equals(x) = true
        // 3. transitive: if x.equals(y) = true && y.equals(z) = true -> x.equals(z) = true
        // 4. consistent: if x.equals(y) = true -> it will always be true
        // 5. x.equals(null) is always false - equals() needs to return false when this occurs rather than throw a NullPointerException
    }
}

// ex: Lion class implements equals() to say that any two Lion objects with the same ID are the same Lion
class Lion {
    private int idNumber;
    private int age;
    private String name;

    public Lion(int idNumber, int age, String name) {
        this.idNumber = idNumber;
        this.age = age;
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {
        if ( !(object instanceof Lion)) return false;
        Lion otherLion = (Lion) object;
        return this.idNumber == otherLion.idNumber;     // we explicitly code this. to be explicit about the object being referenced
        // or we could have: return idNumber == otherLion.idNumber;
    }

    // what is wrong with this method?
    public  boolean equals(Lion object) {
        if (object == null) return false;
        return this.idNumber == object.idNumber;
    } // it's nothing wrong with it, is a good method -> but it does not override equals() from Object because the parameter that it takes is of type 'Lion' and not 'Object'
}

// ■ The easy way to write equals() methods: (real world scenario)
// use the open source library: Apache Commons Lang (http://commons.apache.org/proper/commons-lang/)

// if you want all of the instance variables to be checked:
// public boolean equals(Object obj) {
//      return EqualsBuilder.reflectionEquals(this, obj);
// }

// another overridden method:
// public boolean equals(Object obj) {
//      if ( !(obj instanceof LionEqualsBuilder)) return false;
//      Lion other = (Lion) obj;
//      return new EqualsBuilder().appendSuper(super.equals(obj))
//              .append(idNumber, other.idNumber)
//              .append(name, other.name)
//              .isEquals();
// }

// null and instanceof conditions need to be handled first
