package com.patri.java.ocp._8_IO._3_working_with_streams;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectInputStream_ObjectOutputStream_Classes {

    // - until now we have been managing our data model using classes -> now we want to write these objects to disk

    // - until now all our information that we have worked with was in-memory => this means that the data was not stored anywhere on disk and
    // once the program terminates the data is lost, when we run the program again the data will be different comparing to the data from first run
    // - serialization - appeared because we may want to save the data on disk

    // -■- Serialization = the process of converting an in-memory object to a stored data format on disk
    // -■- Deserialization = the process of converting stored data from disk into an object


    // ■■■ The Serializable Interface

    // java.io Serializable - in order to serialize objects, the class must implement this interface
    // ■ The Serializable Interface = is a tagging or marker interface -> which means it doesn't have any methods
    //                              so any class that implements Serializable is no required to implement any methods

    // ■ why to implement a Serializable interface? - to have the option to serialize the object (write it to disk)
    // ex: String is a Serializable class

    // ■ if one object is marked as Serializable - all the nested objects must be Serializable
    // ex: if a class Cat is Serializable and has a reference to a Tail obj => class Tail must be Serializable and any
    //      object reference within Tail class must be marked as Serializable and so on
    // - trying to serialize an object which is not Serializable or one of its contained classes do not implement Serializable => we will get a NotSerializableException
    // - to avoid this exception -> you can use transient
    // ex: in the cat ex from above if Tail is marked as transient - everything will work ok
    // - the problem with using transient is that: the transient data stored in the object will be lost during the serialization process

    // ■ at serialization and deserialization are ignored: transient instance variables and static class members
    // a static class variable does not belong to one particular instance
    // if you need to store static class information -> it will need to be copied to an instance object and serialized separately

    // ■ Why not mark every class Serializable?
    // because there are some classes like: Thread, Stream for which it will be hard to implement or even impossible
    // to save persistent storage since their work involves managing JVM processes and resources in real time

}

// ex: Animal class that implements Serializable
class Animal implements Serializable {
    private static final long serialVersionUID = 1L;    // this is not required, but it is considered a good practice
                                                        // to do so and update this static class variable anytime you modify the class
    // The serialization process uses the serialVersionUID to identify uniquely a version of the class
    private String name;
    private int age;
    private char type;

    public Animal(String name, int age, char type) {
        this.name = name;
        this.age = age;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public char getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", type=" + type +
                '}';
    }
}

// ■ The serial UID: - identifies in a unique way the version of the class
// it knows when the serialized data for an object is the same as the instance variables from the current version of the class
// - do not rely on the generated serialVersionUID provided by the Java compiler
// - you explicitly declare one in each of your Serializable classes and you have to update it anytime the instance variables in the class are changed


// ■■■ Serializing and Deserializing Objects: use of ObjectInputStream & ObjectOutputStream
// java.io has 2 streams for object serialization and deserialization:
// ■ ObjectInputStream - includes a method to deserialize the object: Object readObject()
// ■ ObjectOutputStream - includes a method to serialize the object: void writeObject(Object)
//                      - if the object is not Serializable, or contains reference to a class that is not Serializable or is marked transient -> NotSerializableException

// ex: read and write Animal data objects
class ObjectStreamSample {
    public static List<Animal> getAnimals(File dataFile) throws IOException, ClassNotFoundException {
        List<Animal> animals = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(dataFile)))) {
            while (true) {
                Object object = inputStream.readObject();
                if (object instanceof Animal)
                    animals.add((Animal)object);
                // we need to check that the object we are reading is actually an instance of the Animal class
                // before explicitly casting it, or else we might get a ClassCastException at runtime
            }
        } catch (EOFException e) {
            // file end reached
        }
        return animals;
    }

    public static void createAnimalsFile(List<Animal> animals, File dataFile) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(dataFile)))) {
            for (Animal animal: animals) {
                outputStream.writeObject(animal);
                // we are iterating over the List object and serializing each Animal object to disk using the writeObject() method
            }
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<Animal> animals = new ArrayList<Animal>();
        animals.add(new Animal("Tommy Tiger", 5, 'T'));
        animals.add(new Animal("Peter Penguin", 8, 'P'));
        // until here the program creates a list of Animal objects in memory with 2 Animal instances

        File dataFile = new File("src\\com\\patri\\java\\ocp\\_8_IO\\_3_working_with_streams\\animal.data");
        createAnimalsFile(animals, dataFile);
        // here it writes the list data into memory to an animal.data file

        System.out.println(getAnimals(dataFile));
        // here it reads the data from the file and outputs the content
    }
}

// ■ EOF check methods:
// you may find code like this, that reads from an InputStream
// while(in.available()>0) -> to check for the end of the stream, rather than checking for an EOFException
// problem with this: it only tells you the number of blocks that can be read without blocking the next caller
// it can return 0 even if there are more bytes to be read
// => the InputStream available() method should never be used to check for the end of the stream

// ■ it is important to check for null values when reading from a serialized data stream


// ■■■ Understanding object creation
// how a deserialized object is created? -> when you deserialize an object - is NOT called the constructor of the serialized class
// ! Java calls the first no-arg constructor for the first nonserializable parent class
// also any static variables or default initializations are ignored

// ex:
class Animal2 implements Serializable {
    private static final long serialVersionUID = 2L;
    private transient String name;
    private transient int age = 10;
    private static char type = 'C';
    {this.age = 14;}

    public Animal2() {
        this.name = "Unknown";
        this.age = 12;
        this.type = 'Q';
    }

    public Animal2(String name, int age, char type) {
        this.name = name;
        this.age = age;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public char getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", type=" + type +
                '}';
    }

}

class ObjectStreamSample2 {
    public static List<Animal2> getAnimals(File dataFile) throws IOException, ClassNotFoundException {
        List<Animal2> animals = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(dataFile)))) {
            while (true) {
                Object object = inputStream.readObject();
                if (object instanceof Animal2)
                    animals.add((Animal2)object);
                // we need to check that the object we are reading is actually an instance of the Animal class
                // before explicitly casting it, or else we might get a ClassCastException at runtime
            }
        } catch (EOFException e) {
            // file end reached
        }
        return animals;
    }

    public static void createAnimalsFile(List<Animal2> animals, File dataFile) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(dataFile)))) {
            for (Animal2 animal: animals) {
                outputStream.writeObject(animal);
                // we are iterating over the List object and serializing each Animal object to disk using the writeObject() method
            }
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<Animal2> animals = new ArrayList<Animal2>();
        animals.add(new Animal2("Tommy Tiger", 5, 'T'));
        animals.add(new Animal2("Peter Penguin", 8, 'P'));
        // until here the program creates a list of Animal objects in memory with 2 Animal instances

        File dataFile = new File("src\\com\\patri\\java\\ocp\\_8_IO\\_3_working_with_streams\\animal2.data");
        createAnimalsFile(animals, dataFile);
        // here it writes the list data into memory to an animal.data file

        System.out.println(getAnimals(dataFile));
        // here it reads the data from the file and outputs the content
    }
}
// output: [Animal{name='null', age=0, type=P}, Animal{name='null', age=0, type=P}]
// - the transient values won't be included in the serialization process -> 'name' and 'age' will be left out of the serialized file
// - the values of age being set to 10, 12, 14 in the class are all ignored when the object is deserialized, as no class constructor or default initializations are used

// in the output:
// - the values for 'name' and 'age' are lost on serialization and not set again during deserialization
// JVM initializes these variables with the default values based on the data types String and int -> null and 0
// - because the variable 'type' is static -> it is not serialized to disk
// - the value for 'type' is the last value set in the program