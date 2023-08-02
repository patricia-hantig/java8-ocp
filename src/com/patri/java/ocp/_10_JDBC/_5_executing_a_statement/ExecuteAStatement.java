package com.patri.java.ocp._10_JDBC._5_executing_a_statement;

import java.sql.*;

public class ExecuteAStatement {
    public static void main(String[] args) throws SQLException {
        // Now that we have a Statement -> we can run a SQL statement
        Connection conn = DriverManager.getConnection("jdbc:derby:zoo");
        System.out.println(conn);
        Statement statement = conn.createStatement();

        // ■■■ How to run a SQL depends on what kind of SQL statement it is - we have 3 types of statements:

        // ■ statement that changes the data in the table: SQL statements that begin with DELETE, INSERT, UPDATE
        //      - they use the method: executeUpdate() = takes the SQL statement to run as a parameter and returns an integer (the number of rows that were inserted, deleted or changed)
        // ex: uncomment the following code & run it
        /*int result = statement.executeUpdate("INSERT INTO species VALUES (13, 'Deer', 3)");
        System.out.println(result);
        result = statement.executeUpdate("UPDATE species SET name = '' WHERE name = 'None'");
        System.out.println(result);
        result = statement.executeUpdate("DELETE FROM species WHERE id = 13");
        System.out.println(result);*/

        // ■ statement that reads data from the table: SQL statements that begin with SELECT
        //      - they use the method: executeQuery() = takes the SQL statement to run as a parameter and returns a ResultSet (which contains the lines and columns)
        // ex: uncomment the following code & run it
        /*ResultSet resultSet = statement.executeQuery("SELECT * FROM species");*/

        // next: how to process the ResultSet

        // ■ statement for query and update:
        //      - they use the method: execute() = that can run a query or an update and returns a boolean (true -> ResultSet (query); false -> integer (update))
        // ex: uncomment the following code & run it
        /*String sql = "SELECT * FROM species";
        String sql2 = "UPDATE species SET name = '' WHERE name = 'None'";
        boolean isResultSet = statement.execute(sql);
        if (isResultSet) {
            ResultSet resultSet = statement.getResultSet();
            System.out.println("ran a query");
        } else {
            int result = statement.getUpdateCount();
            System.out.println("ran an update");
        }*/

        // ■■■ What happens if we use the wrong method for a SQL statement?
        // ex: uncomment the following code & run it
        /*int resultSet = statement.executeUpdate("SELECT * FROM species");*/
        // we get the SQLException: Statement.executeUpdate() cannot be called with a statement that returns a ResultSet

        // ex: uncomment the following code & run it
        /*ResultSet resultSet = statement.executeQuery("UPDATE species SET name = '' WHERE name = 'None'");*/
        // we get the SQLException: Statement.executeQuery() cannot be called with a statement that returns a row count

        // we can't get a compiler error because SQL is a String
        // we get an exception because the driver can't translate the query into the expected return type

        // ■■■ PreparedStatement - is used in REAL WORLD SCENARIO
        // - in real life you should not used Statement directly -> you should use a subclass called PreparedStatement
        // - Advantages: performance, security and readability
        // ■ performance: in most programs you run similar queries multiple times - a preparedStatement figures out a plan to run the SQL and remembers it
        // ■ security:
        //      ex: lets say we have this code:
        /*Statement stmt = conn.createStatement();
        String name = "";
        String sql = "DELETE FROM animal WHERE name = '" + name + "'";
        System.out.println(sql);
        stmt.executeUpdate(sql);*/
        // if name = "Asian Elephant"           -> OK - just one row gets deleted
        // if name = "any" or if name = "1=1"   -> NOT OK - deletes every row in the table - SQL injection
        //      ex: with PreparedStatement - JDBC driver takes care of all the problems for us
        /*String name = "";
        PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM animal where name = ?");
        preparedStatement.setString(1, name);
        preparedStatement.executeUpdate();*/
        // this is OK for all cases
        // ■ readability: we get rid of string concatenation ( + "..." + "..." + ...)

        // ■■■ SQL runnable by execute method:
        //          Method      |   DELETE  |   INSERT  |   SELECT  |   UPDATE
        // ----------------------------------------------------------------------
        // stmt.execute()       |   YES     |   YES     |   YES     |   YES
        // stmt.executeQuery()  |   NO      |   NO      |   YES     |   NO
        // stmt.executeUpdate() |   YES     |   YES     |   NO      |   YES
        // ----------------------------------------------------------------------

        // ■■■ SQL types of executes:
        //          Method      |   Return Type |       What is returned for SELECT |   What is returned for DELETE/INSERT/UPDATE
        // ---------------------------------------------------------------------------------------------------------------------
        // stmt.execute()       |   boolean     |               true                |               false
        // stmt.executeQuery()  |   ResultSet   | The rows and columns are returned |               N/A
        // stmt.executeUpdate() |   int         |               N/A                 | Number of rows removed/added/changed
        // ---------------------------------------------------------------------------------------------------------------------
    }
}
