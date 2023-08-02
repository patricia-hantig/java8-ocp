package com.patri.java.ocp._1_advanced_class_design._1_review_OCA;

public class AccessModifiers {
    // ■ The Access Modifiers are: 'public', 'protected', 'private' and 'default'

    // public       - has access from all classes
    // protected    - has access from all classes within the same package and from all subclasses
    // default      - has access from all classes within the same package
    // private      - has access only from the same class

    //                          Can access                                      | member is private | member is default | member is protected | member is public
    // -----------------------------------------------------------------------------------------------------------------------------------------------------------
    // Member in the same class                                                 |       YES         |       YES         |       YES           |         YES
    // Member in another class in the same package                              |       NO          |       YES         |       YES           |         YES
    // Member in a superclass in a different package                            |       NO          |       NO          |       YES           |         YES
    // Method/field in a class (that is not superclass) in a different package  |       NO          |       NO          |       NO            |         YES
    // -----------------------------------------------------------------------------------------------------------------------------------------------------------
}

// Example:
// we have the following code:
  /*  public static void main(String[] args) {
        BigCat cat = new BigCat();
        System.out.println(cat.name);
        System.out.println(cat.hasFur);
        System.out.println(cat.hasPaws);
        System.out.println(cat.id);
    }*/

// Suppose each of the classes below has the 'main method' from above that instantiates a BigCat and
// tries to print out all four variables. Which variables will be allowed in each case?

// package cat;
class BigCat {
    public String name = "cat";
    protected boolean hasFur = true;
    boolean hasPaws = true;
    private int id;

    /*  public static void main(String[] args) {
        BigCat cat = new BigCat();
        System.out.println(cat.name);                   // compiles
        System.out.println(cat.hasFur);                 // compiles
        System.out.println(cat.hasPaws);                // compiles
        System.out.println(cat.id);                     // compiles
    }*/
}

// package cat.species;
class Lynx extends BigCat {
    /*  public static void main(String[] args) {
        BigCat cat = new BigCat();
        System.out.println(cat.name);                   // compiles
        System.out.println(cat.hasFur);                 // does not compile - the code is being accessed via the variable rather than by inheritance, it does not benefit from protected
                                                        // if it was: Lynx cat = new Lynx(); , Lynx would be able to access 'cat.hasFur' using protected access
        System.out.println(cat.hasPaws);                // does not compile - 'hasPaws' has default access and package 'cat' is different from package 'cat.species'
        System.out.println(cat.id);                     // does not compile - 'id' has private access
    }*/
}

// package cat;
class CatAdmirer {
    /*  public static void main(String[] args) {
        BigCat cat = new BigCat();
        System.out.println(cat.name);                   // compiles
        System.out.println(cat.hasFur);                 // compiles
        System.out.println(cat.hasPaws);                // compiles
        System.out.println(cat.id);                     // does not compile - 'id' has private access
    }*/
}

// package mouse;
class Mouse {
    /*  public static void main(String[] args) {
        BigCat cat = new BigCat();
        System.out.println(cat.name);                   // compiles
        System.out.println(cat.hasFur);                 // does not compile - 'hasFur' has protected access and package 'cat' is different from package 'mouse' & 'BigCat' and 'Mouse' are not related
        System.out.println(cat.hasPaws);                // does not compile - 'hasPaws' has default access and package 'cat' is different from package 'mouse'
        System.out.println(cat.id);                     // does not compile - 'id' has private access
    }*/
}

