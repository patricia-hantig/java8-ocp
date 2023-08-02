package com.patri.java.ocp._10_JDBC._4_obtaining_a_statement;

import java.sql.*;

public class ObtainAStatement {
    public static void main(String[] args) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:derby:zoo");
        System.out.println(conn);

        // to be able to run a SQL => you need to tell a Statement about it
        // getting a Statement from a Connection:
        Statement statement = conn.createStatement();

        // ■ Statement = is one of the four interfaces and it represents a SQL statement that you want to run using the Connection

        // another signature: createStatement(ResultSet Type, ResultSet Concurrency Mode)
        Statement statement1 = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

        // ■■ Choose a ResultSet Type
        // - by default a ResultSet is in TYPE_FORWARD_ONLY - this means that you can go through the data in the order in which it was retrieved
        // - other types for ResultSEt Type are:
        //      TYPE_SCROLL_INSENSITIVE & TYPE_SCROLL_SENSITIVE
        //      - both allow you to go through the data in any order (forward or backward)
        //      - for both think of it like scrolling in a browser -> you can scroll up or down -> you can go to a specific point in the result
        //      - the difference is what happens when data changes in the database while you are scrolling
        //              - TYPE_SCROLL_INSENSITIVE -> you have a static view of what the ResultSet looked like when you did the query
        //                                          - if the data changed in the table you will see it as it was when you did the query
        //              - TYPE_SCROLL_SENSITIVE -> you will see the latest data when scrolling through the ResultSet
        //                                      - most databases and database drivers don't actually support the TYPE_SCROLL_SENSITIVE mode
        //                                          this means that you will get a Statement that is TYPE_SCROLL_INSENSITIVE automatically

        // ResultSet type options:
        //          ResultSet Type          |   Can go Backward?    | See latest data from database table?  |   Supported by most drivers?
        // ---------------------------------------------------------------------------------------------------------------------------------
        // ResultSet.TYPE_FORWARD_ONLY      |           NO          |                   NO                  |               YES
        // ResultSet.TYPE_SCROLL_INSENSITIVE|           YES         |                   NO                  |               YES
        // ResultSet.TYPE_SCROLL_SENSITIVE  |           YES         |                   YES                 |               NO


        // ■■ Choose a ResultSet Concurrency Mode
        // - by default a ResultSet is in CONCUR_READ_ONLY mode - this means that you can't update the ResultSet
        //      most of the time you will use INSERT, UPDATE or DELETE SQL statements to change the database
        // - there is one other mode that you can request when creating a Statement: CONCUR_UPDATABLE - which can change the database through the ResultSet
        //      - some DB drivers does not support CONCUR_UPDATABLE -> this means that you will get a Statement that is in CONCUR_READ_ONLY mode
        //      - updatable result set is used on rare occasions

        // ResultSet concurrency mode options:
        //      ResultSet Type          |   Can read data?  | Can update data?  |   Supported by all drivers?
        // ----------------------------------------------------------------------------------------------------
        // ResultSet.CONCUR_READ_ONLY   |       YES         |       NO          |           YES
        // ResultSet.CONCUR_UPDATABLE   |       YES         |       YES         |           NO

    }
}
