package com.patri.java.ocp._10_JDBC._6_getting_data_from_resultSet;

import java.sql.*;

public class ScrollingResultSet {
    public static void main(String[] args) throws SQLException {

        // ■■■ Scrollable ResultSet = allows you to position the cursor at any row

        // ■ Methods for scrollable ResultSet:
        // - we know next() method = it moves forward one row and returns 'true' if pointing to a valid row of data
        // - there is also previous() method = it moves backward one row and returns 'true' if pointing to a valid row of data
        // - there are also methods to start at the beginning and end of the ResultSet: first() & last() = return a boolean for whether they were successful at finding a row
        // - beforeFirst() and afterLast() = return type is void - because it is always possible to get to a spot that doesn't have data
        // - absolute() = it takes the row number to which you want to move the cursor as a parameter
        //              - a positive number moves the cursor to that number row
        //              - 0 moves the cursor to a location immediately before the first row
        //              - a negative number means to start counting from the end of the ResultSet rather than from the beginning
        // - relative() = moves forward or backward the requested number of rows; it returns a boolean if the cursor is pointing to a row with data

        // - To what points the scrollable ResultSet methods():
        //  beforeFirst()   ->  id integer  |   name character varying(255) |   num_acres numeric
        //  first()         ->      1       |       African Elephant        |   7.5
        //  next()          ->      2       |       Zebra                   |   1.2
        //  next()          ->      10      |       Deer                    |   0
        //  last()          ->      11      |       Deer                    |   0
        //  afterLast()     ->

        // - Absolute rows:
        //  absolute(0)     ->  id integer  |   name character varying(255) |   num_acres numeric
        //  absolute(1)     ->      1       |       African Elephant        |   7.5
        //  absolute(2)     ->      2       |       Zebra                   |   1.2
        //  absolute(3)     ->     10       |       Deer                    |   0
        //  absolute(4)     ->     11       |       Deer                    |   0
        //  absolute(5)     ->

        // - Negative absolute rows:
        //  absolute(-5)     ->  id integer  |   name character varying(255) |   num_acres numeric
        //  absolute(-4)     ->      1       |       African Elephant        |   7.5
        //  absolute(-3)     ->      2       |       Zebra                   |   1.2
        //  absolute(-2)     ->     10       |       Deer                    |   0
        //  absolute(-1)     ->     11       |       Deer                    |   0
        // you can see that -1 is the last row

        // ■ next(), previous(), first(), last(), beforeFirst() and afterLast()
        // ex:
        Connection conn = DriverManager.getConnection("jdbc:derby:zoo");
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); // we create a scrollable result set type
        ResultSet resultSet = statement.executeQuery("SELECT id FROM species ORDER BY id");
        resultSet.afterLast();                                              // cursor is after the last row in the result
        System.out.println(resultSet.previous());               // true     // cursor moves back one
        System.out.println(resultSet.getInt(1));    // 11
        System.out.println(resultSet.previous());               // true     // cursor moves back one
        System.out.println(resultSet.getInt(1));    // 10
        System.out.println(resultSet.last());                   // true     // cursor is at the last row in the result
        System.out.println(resultSet.getInt(1));    // 11
        System.out.println(resultSet.first());                  // true     // cursor is at the first row in the result
        System.out.println(resultSet.getInt(1));    // 1
        System.out.println(resultSet.next());                   // true     // cursor moves forward one
        System.out.println(resultSet.getInt(1));    // 2
        resultSet.beforeFirst();                                            // cursor is before the first row in the result
        //System.out.println(resultSet.getInt(1));    // SQLException: Invalid cursor state - no current row. - there is no data to read before the first row
        System.out.println();

        // ex: query that doesn't return any rows
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("SELECT id FROM species WHERE id = -99");
        System.out.println(rs.first()); // false
        System.out.println(rs.last());  // false
        // there aren't any rows => so we don't have any data
        System.out.println();

        // ■ absolute()
        // ex:
        // we will use the Animal table:
        //  id integer  |   species_id integer  |   name character varying(255) |   date_born timestamp without time zone   -> absolute(0)
        // ---------------------------------------------------------------------------------------------------------------
        //      1       |           1           |           Elsa                |           2001-05-06 02:15:00             -> absolute(1)  - absolute(-5)
        //      2       |           2           |           Zelda               |           2002-08-15 09:12:00             -> absolute(2)  - absolute(-4)
        //      3       |           1           |           Ester               |           2002-09-09 10:36:00             -> absolute(3)  - absolute(-3)
        //      4       |           1           |           Eddie               |           2010-06-08 01:24:00             -> absolute(4)  - absolute(-2)
        //      5       |           2           |           Zoe                 |           2005-11-12 03:44:00             -> absolute(5)  - absolute(-1)

        Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet r = st.executeQuery("SELECT id FROM animal ORDER BY id");
        System.out.println(r.absolute(2));              // true
        System.out.println(r.getString("id"));  // 2
        System.out.println(r.absolute(0));              // false
        System.out.println(r.absolute(5));              // true
        System.out.println(r.getString("id"));  // 5
        System.out.println(r.absolute(-2));             // true
        System.out.println(r.getString("id"));  // 4
        System.out.println();

        // ■ relative() - moves forward or backward the requested number of rows; it returns a boolean if the cursor is pointing to a row with data
        // ex:
        Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rSet = s.executeQuery("SELECT id FROM animal ORDER BY id");
        System.out.println(rSet.next());                        // true
        System.out.println(rSet.getString("id"));   // 1
        System.out.println(rSet.relative(2));               // true  - moves forward two rows to row 3
        System.out.println(rSet.getString("id"));   // 3
        System.out.println(rSet.relative(-1));               // true - moves backward one to row 2
        System.out.println(rSet.getString("id"));   // 2
        System.out.println(rSet.relative(4));               // false - tries to move forward 4 rows, which would position the cursor by row 6
                                                                    // There is no row 6 (this is after the last row) => the method returns false

        // ■ Conclusion for navigating a ResultSet:
        //              Method          |                           Description                          |   Requires Scrollable ResultSet
        // -------------------------------------------------------------------------------------------------------------------------------
        // boolean absolute(int rowNum) | moves cursor to the specified row number                       |               Yes
        // void afterLast()             | moves cursor to a location immediately after the last row      |               Yes
        // void beforeFirst()           | moves cursor to a location immediately before the first row    |               Yes
        // boolean first()              | moves cursor to the first row                                  |               Yes
        // boolean last()               | moves cursor to the last row                                   |               Yes
        // boolean next()               | moves cursor one row forward                                   |               No
        // boolean previous()           | moves cursor one row backward                                  |               Yes
        // boolean relative(int rowNum) | moves cursor forward or backward the specified number of rows  |               Yes


    }
}
