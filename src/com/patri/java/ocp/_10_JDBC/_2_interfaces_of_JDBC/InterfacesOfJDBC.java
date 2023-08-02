package com.patri.java.ocp._10_JDBC._2_interfaces_of_JDBC;

import java.sql.*;

public class InterfacesOfJDBC {
    public static void main(String[] args) throws SQLException {

        // ■■■ 4 Key Interfaces of JDBC
        // - the interfaces are declared in the JDK (like List, Path that are also in the JDK)
        // - interfaces need a concrete class to implement them in order to be useful
        // - the concrete classes come from the JDBC driver
        // - each database has a different JAR file with these concrete classes
        // ex: PostgreSQL's JAR: postgresql-9.4-1201.jdbc4.jar
        // ex: MySQL's JAR: mysql-connectorjava-5.1.36.jar
        // - the driver JAR contains an implementation of these key interfaces -> the provided implementations know how to communicate with a database

        // ■ Key JDBC interfaces:
        // ------------------------------------------------------------------
        //  Interfaces in the JDK   |   Implementation in the FOO driver JAR
        // ------------------------------------------------------------------
        //      Driver              |               FooDriver
        //      Connection          |               FooConnection
        //      Statement           |               FooStatement
        //      ResultSet           |               FooResultSet
        // ------------------------------------------------------------------

        // ■ What do these interfaces do?
        // - Driver     = knows how to get a connection to the database
        // - Connection = knows how to communicate with the database
        // - Statement  = knows how to run the SQL
        // - ResultSet  = knows what was returned by the SELECT query
        // all database classes are in the package: java.sql

        // example: show what JDBC code looks like end to end
        String url = "jdbc:derby:zoo";
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT name FROM animal")) {
            while (resultSet.next())
                System.out.println(resultSet.getString(1));
        }

        // the code from above outputs the result for the query: SELECT name FROM animal
    }
}
