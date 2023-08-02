package com.patri.java.ocp._10_JDBC._1_introduction_relational_db_and_sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SetupDerbyDatabase {
    public static void main(String[] args) throws Exception{
        // in Java 8 derby is no longer part of the distribution of JDK
        // you must download the jar manually and then use the path to the downloaded jar
        // in our case derby.jar can be found in package: _10_JDBC

        // Next step is to add it to the classpath:
        // In IntelliJ IDEA:
        // 1. Click File from the toolbar
        // 2. Select Project Structure option
        // 3. Select Modules at the left panel
        // 4. Select Dependencies tab
        // 5. Select + icon
        // 6. Select 1 JARs or directories option

        // The program:
        String url = "jdbc:derby:zoo;create=true";
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(
                    "CREATE TABLE species ("
                    + "id INTEGER PRIMARY KEY, "
                    + "name VARCHAR(255), "
                    + "num_acres DECIMAL)"
            );

            statement.executeUpdate(
                    "CREATE TABLE animal ("
                            + "id INTEGER PRIMARY KEY, "
                            + "species_id INTEGER, "
                            + "name VARCHAR(255), "
                            + "date_born TIMESTAMP)"
            );

            statement.executeUpdate("INSERT INTO species VALUES (1, 'African Elephant', 7.5)");
            statement.executeUpdate("INSERT INTO species VALUES (2, 'Zebra', 1.2)");

            statement.executeUpdate("INSERT INTO animal VALUES (1, 1, 'Elsa', '2001-05-06 02:15:00')");
            statement.executeUpdate("INSERT INTO animal VALUES (2, 2, 'Zelda', '2002-08-15 09:12:00')");
            statement.executeUpdate("INSERT INTO animal VALUES (3, 1, 'Ester', '2002-09-09 10:36:00')");
            statement.executeUpdate("INSERT INTO animal VALUES (4, 1, 'Eddie', '2010-06-08 01:24:00')");
            statement.executeUpdate("INSERT INTO animal VALUES (5, 2, 'Zoe', '2005-11-12 03:44:00')");

            ResultSet rs = statement.executeQuery("SELECT * FROM species");
            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                float num_acres = rs.getFloat("num_acres");

                System.out.println(id + ", " + name + ", " + num_acres);
            }
        }

        // this program connects to the database and creates two tables
        // then it loads data into those tables and gets data from first table to test that the tables were populated

        // running this example creates a folder for the database called: zoo at the same level with src
    }
}
