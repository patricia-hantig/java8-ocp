package com.patri.java.ocp._2_design_patterns_and_principles._4_design_principles;

public class JavaBeans {
    // ■ JavaBean = is a design principle for encapsulating data in an object

    // ■ Rules for naming JavaBeans:
    //                              Rule                        |                       Example
    // -----------------------------------------------------------------------------------------------------------------------------
    // Properties are private                                   |   private int age;
    // Getter for non-boolean properties begin with get         |   public int getAge() {...}
    // Getter for boolean properties may begin with is or get   |   public boolean isBird() {...} or public boolean getBird() {...}
    // Setter methods begin with set                            |   public void setAge(int age) {...}
    // In defining getters and setters we use camelCase         |   setNumChildren(), getAnimalType()

    // for boolean we have is(), get() but for Boolean we have only get()
    // example:  which follow JavaBean rules?
    private boolean playing;
    private Boolean dancing;
    public boolean isPlaying() { return playing; } // correct JavaBean
    public boolean getPlaying() { return playing; } // correct JavaBean
    public Boolean isDancing() { return dancing; } // wrong JavaBean - Boolean wrapper should start with 'get'

    public String name;                     // doesn't follow JavaBean rules! -> is 'public' instead of 'private'
    public String name() { return name; }   // doesn't follow JavaBean rules! -> is not a proper getter - should be getName()
    public void updateName(String n) {}     // doesn't follow JavaBean rules! -> incorrect setter - should be setName()
    public void setname(String n) {}        // doesn't follow JavaBean rules! -> incorrect setter - should be setName()
}
