package com.patri.java.ocp._7_concurrency._7_threading_problems;

// race conditions = occurs when 2 tasks, which should be completed sequentially, are completed at the same time
public class RaceConditionsExample {

    // example of race conditions:

    // 2 zoo patrons, Olivia and Sophia want to create a new account on the website, both want to use the same username: ZooFan and
    // each send requests to create the account at the same time
    // What result does the web server return when both users attempt to create an account with the same username?
    // possible outcomes for this race condition
    // 1. Both users are able to create accounts with username ZooFan
    // 2. Both users are unable to create an account with username ZooFan, returning an error message to both users
    // 3. One user is able to create the account with the username ZooFan, while the other user receives an error message

    // 1 - worst outcome => could cause serious problems and break numerous invariants in the system
    // (how they login if they have the same username?) -> it causes significant and potentially unrecoverable data problems
    // 2 - the data is protected => the users are free to try again with the same username (ex if one user submits their request
    // a few seconds before the other, they might avoid another race condition)
    // 3 - the best solution to this type of race condition => data is protected and unlike scenario 2: at least one user is able
    // to move forward on the first request - in this case we know who won the race
    // the user who didn’t win the race will get a clearer error message because we are sure that the account username
    // is no longer available in the system

    // race conditions lead to invalid data if they are not properly handled!!!!
    // SOLUTION: to use a monitor to synchronize on the relevant overlapping task or we could also use singletons
}
