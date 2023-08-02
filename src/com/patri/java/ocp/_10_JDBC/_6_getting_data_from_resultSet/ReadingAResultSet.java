package com.patri.java.ocp._10_JDBC._6_getting_data_from_resultSet;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ReadingAResultSet {
    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:derby:zoo");
        Statement statement = conn.createStatement();
        // we will start with the most common type of ResultSet: forward-only
        // then we will show you how to work with a scrollable ResultSet

        // ■■■ forward-only ResultSet:
        // ex: loop to look at each row - best way => using the column name
        Map<Integer, String> idToNameMap = new HashMap<>();
        ResultSet resultSet = statement.executeQuery("SELECT id, name FROM species");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            idToNameMap.put(id, name);
        }
        System.out.println(idToNameMap);

        // ■ ResultSet has a cursor = which points to the current location in the data
        // ResultSet resultSet = statement.executeQuery("SELECT id, name FROM species"); - here cursor starts pointing out to the location before the ResultSet
        // resultSet.next() - on the first iteration resultSet.next() returns true -> the cursor moves to point to the first row of data
        //                  - on the second iteration resultSet.next() returns true -> the cursor moves to point to the second row if data
        //                  - the next call of resultSet.next() returns false -> the cursor advances past the end of the data => false = there is no data available to get

        // initial position     ->      id integer  | name character varying(255)   | num_acres numeric
        // rs.next() true       ->          1       |       African Elephant        |   7.5
        // rs.next() true       ->          2       |               Zebra           |   1.2
        // rs.next() false      ->

        // ex: loop to look at each row - another way => using an index
        Map<Integer, String> idToNameMap2 = new HashMap<>();
        ResultSet resultSet2 = statement.executeQuery("SELECT id, name FROM species");
        while (resultSet2.next()) {
            int id = resultSet2.getInt(1);              // ! Remember that JDBC starts counting with 1 rather than 0
            String name = resultSet2.getString(2);
            idToNameMap2.put(id, name);
        }
        System.out.println(idToNameMap2);
        // the column name is better because is clearer what is going on when reading the code

        // sometimes you want to get only one row from the table, or only one piece of data or just returning the number of rows in the table
        // ex: returning the number of rows in the table
        ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM animal");
        if (rs.next())
            System.out.println(rs.getInt(1));
        // it is important to check that rs.next() returns true before calling a getter on the ResultSet
        // ex: alternatively you can use a column name:
        ResultSet rs2 = statement.executeQuery("SELECT COUNT(*) AS count FROM animal");
        if (rs2.next())
            System.out.println(rs2.getInt("count"));
        // or if we don't want to change the sql query
        ResultSet rs3 = statement.executeQuery("SELECT COUNT(*) FROM animal");
        if (rs3.next())
            System.out.println(rs3.getInt("1"));        // column name automatically is equal with the columnIndex as String

        // ■ some bad code examples:
        // ex1: bad code
        // int id = rs.getInt(0); -> throws SQLException - JDBC starts counting with 1 rather than 0

        // ex2: bad code
        ResultSet rs4 = statement.executeQuery("SELECT * FROM animal WHERE name = 'Not in table'");
        System.out.println(rs4.next());                 // false
        //System.out.println(rs4.getInt(1));            // SQLException: Invalid cursor state - no current row -> the result set cursor does not point to a valid position

        // ex3: bad code
        ResultSet rs5 = statement.executeQuery("SELECT COUNT(*) FROM animal");
        //rs5.getInt(1);            // SQLException: Invalid cursor state - no current row -> Not calling rs.next() is a problem!
        //                              the result set cursor is still pointing to a location before the first row -> so the getter has nothing to point to

        // ex4: bad code
        ResultSet rs6 = statement.executeQuery("SELECT COUNT(*) FROM animal");
        rs6.next();
        //rs6.getInt(0);            // SQLException: Column '0' not found. -> column index begin with 1

        // ex5: bad code
        ResultSet rs7 = statement.executeQuery("SELECT id FROM animal");
        rs7.next();
        //rs7.getInt("badColumn");  // SQLException: Column 'badColumn' not found. - trying to get a column that isn't in the ResultSet is BAD as an invalid column index

        // ■ Conclusion:    - always use an if statement or while loop when calling rs.next()
        //                  - column indexes begin with 1

    }
}
