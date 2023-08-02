package com.patri.java.ocp._10_JDBC._7_closing_db_resource;

import java.sql.*;

public class ClosingDbResources {
    public static void main(String[] args) throws SQLException {

        // ■■■ Closing DB resources in Java 8

        // is important to close resources when you are finished with them - apply also for JDBC resources
        // JDBC resources = are expensive to create ( ex: Connection)
        // Not closing JDBC resources => creates a resource leak that will slow down your program

        // ex:
        String url = "jdbc:derby:zoo";
        try (Connection conn = DriverManager.getConnection(url);
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT name FROM animal")) {
            while (resultSet.next())
                System.out.println(resultSet.getString(1));
        }
        System.out.println();
        // this code uses try-with-resources syntax = closes the resources in the reverse order from which they were opened
        // -> ResultSet is closed first, then Statement, then Connection

        // ■■■ Closing DB resources before Java 8 (without try-with-resources)
        // ex:
        String urlString = "jdbc:derby:zoo";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(urlString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT name FROM animal");
            while (resultSet.next())
                System.out.println(resultSet.getString(1));
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            closeConnection(connection);
        }
        // - Each of the helper methods to close a resource has a try/catch block that ignores any SQLException thrown on closing => Java 8 example is better -> it doesn't lose exceptions
        // - While it is a good practice to close all 3 resources -> it's NOT necessary - closing a JDBC resource should close any resources that it created
        //      - closing a Connection also closes the Statement and ResultSet
        //      - closing a Statement also closes the ResultSet


        // ■ JDBC automatically closes a ResultSet when you run another SQL statement from the same Statement
        // ex:
        try(Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM animal")) {
            if (rs.next())
                System.out.println(rs.getInt(1));

            ResultSet rs2 = stmt.executeQuery("SELECT COUNT(*) FROM animal");                       // rs closes here - because the same statement runs another query
            int num = stmt.executeUpdate("UPDATE animal SET name = 'clear' WHERE name = 'other'");
            // rs2 closes here - because the same statement runs another SQL statement
            // Then the try-with-resources statement runs and closes the Statement and Connection objects
        }
    }

    private static void closeConnection(Connection connection) {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private static void closeStatement(Statement statement) {
        try {
            if (statement != null)
                statement.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private static void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null)
                resultSet.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //  ! It is very important to close resources in the right order
}
