package com.patri.java.ocp._2_design_patterns_and_principles._1_design_an_interface;

import java.util.List;

public class PurposeOfInterface {
    // purpose of an interface = can facilitate application development by enabling the teams
    //                          to create applications in parallel, rather than being directly
    //                          dependent on each other

    // example: 2 teams can work together to develop a one-page standard interface at the start of a project
    //          one team develops code that uses the interface
    //          the other team develops code that implements the interface
    //          at the end of the project the 2 teams can combine their implementations
    //          because both developed with the same interface -> they will be compatible

    // in real world scenario: mock objects
    // we can use an interface without it being implemented: mock objects - simulates the object
    // that implements the interface -> is useful in testing

    // The developer using the interface can create a temporary mock object (dummy code), which simulates
    // the real object that implements the interface with a simple implementation
    // the mock objects only serves as a placeholder for the real implementation

}

// example for: Mock objects
// imagine that you were working on a racing application with the code that calculates the winners
// handled by a different team - both teams agreed on a RaceManager interface
// your team using the interface and the other team implementing it

class Animal {}
class Tortoise extends Animal {}
class Hare extends Animal {} // hare = large rabbit
interface RaceManager {
    public Animal getWinner(List<Animal> animals);
}

// The good news is that your team has finished its part of the project first
// The bad news is that the other team has nothing for you to test with
// while waiting for the other team to finish -> you can create a mock version of RaceManager

class DummyRaceManager implements RaceManager {
    @Override
    public Animal getWinner(List<Animal> animals) {
        return animals == null || animals.size() == 0 ? null : animals.get(0);
    }
}

// this code isn't particularly great (it returns the first item in the list) but is useful for testing purpose
// you can also write a version that always return a Tortoise or Hare

// the goal for mocked objects is to give you something temporary that you can work with
// in our example the implementation of getWinner() could have hundreds of lines of code


