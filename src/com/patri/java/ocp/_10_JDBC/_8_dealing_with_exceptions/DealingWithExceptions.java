package com.patri.java.ocp._10_JDBC._8_dealing_with_exceptions;

import java.sql.*;

public class DealingWithExceptions {
    public static void main(String[] args) {
        // ex: catch a SQLException:
        String url = "jdbc:derby:zoo";
        try (Connection conn = DriverManager.getConnection(url);
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT not_a_column FROM animal")) {
            while (resultSet.next())
                System.out.println(resultSet.getString(1));
        } catch (SQLException e) {
            System.out.println(e.getMessage());     // returns a readable message about what went wrong
            System.out.println(e.getSQLState());    // returns a code about what went wrong - you can Google the name of your database and the SQL state to get more information about the error
            System.out.println(e.getErrorCode());   // is a database-specific code
        }

        // Output:
        // Column 'NOT_A_COLUMN' is either not in any table in the FROM list or appears within a join specification and
        // is outside the scope of the join specification or appears in a HAVING clause and is not in the GROUP BY list.
        // If this is a CREATE or ALTER TABLE  statement then 'NOT_A_COLUMN' is not a column in the target table.
        //42X04
        //30000
    }
}
