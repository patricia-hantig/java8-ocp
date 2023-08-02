package com.patri.java.ocp._6_exceptions_and_assertions._4_rethrowing_exceptions;

// For rethrowing exceptions: there is a common pattern to log and then throw the exception
// we can do this with multi-catch or with "Exception"
// a better solution is to use "Exception" to not have duplicate code

import java.sql.SQLException;
import java.time.format.DateTimeParseException;

public class Example {

    //method that throws one checked exception and one runtime exception
    public void parseData() throws SQLException, DateTimeParseException {
    }

    // BAD rethrowing = method call done with multi-catch
    // contains duplicated code
    // if parseData() throws NPE, it will not be catch by this method
    // if parseData() will throw a new exception, it will have to be written in 2 places in this method
    public void  multiCatch() throws SQLException, DateTimeParseException {
        try {
            parseData();
        } catch (SQLException | DateTimeParseException e) {
            System.err.println(e);
            throw e;
        }
    }

    // GOOD rethrowing = method call done with Exception
    // doesn't contain duplicated code
    // if parseData() throws NPE, it will be catch by this method
    // if parseData() will throw a new exception, it will have to be written only in one place in this method
    public void  rethrowing() throws SQLException, DateTimeParseException {
        try {
            parseData();
        } catch (Exception e) {
            System.err.println(e);
            throw e;
        }
    }

}
