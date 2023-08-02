package com.patri.java.ocp._10_JDBC._6_getting_data_from_resultSet;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class GettingDataForAColumn {
    public static void main(String[] args) throws SQLException {

        // ■■■ ResultSet get methods:
        //      Method Name     |       Return Type     |   Database Type - what could be in the DB
        // -----------------------------------------------------------------------------------------
        //  getBoolean()        |   boolean             |   BOOLEAN
        //  getDate()           |   java.sql.Date       |   DATE
        //  getDouble()         |   double              |   DOUBLE
        //  getInt()            |   int                 |   INTEGER
        //  getLong()           |   long                |   BIGINT
        //  getObject()         |   Object              |   Any type
        //  getString()         |   String              |   CHAR, VARCHAR
        //  getTime()           |   java.sql.Time       |   TIME
        //  getTimeStamp()      |   java.sql.TimeStamp  |   TIMESTAMP
        // -----------------------------------------------------------------------------------------

        // there are getByte() and getFloat() methods
        // there is no getChar() method

        // ■ we have 3 date types: getDate(), getTime() and getTimeStamp()

        // - getDate() = returns just the date part of the value
        // ex1: we want to know the date elephant Elsa was born  -> in db we have: 2001–05–06 02:15
        Connection conn = DriverManager.getConnection("jdbc:derby:zoo");
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT date_born FROM animal WHERE name = 'Elsa'");
        if (rs.next()) {
            Date sqlDate = rs.getDate(1);       // getDate()    - JDBC returns just the date part of the value: year, month and date
                                                            //              - it returns a java.sql.Date obj - older class
            LocalDate localDate = sqlDate.toLocalDate();    // but Java 8 converts the date to a new LocalDate type
            System.out.println(localDate);  // 2001-05-06
        }

        // - getTime() = returns just the time part of the value
        // ex2: we want to know what time of day elephant Elsa was born  -> in db we have: 2001–05–06 02:15
        ResultSet rs2 = statement.executeQuery("SELECT date_born FROM animal WHERE name = 'Elsa'");
        if (rs2.next()) {
            Time sqlDate = rs2.getTime(1);       // getTime()    - JDBC returns just the time part of the value: hours and minutes
                                                            //              - it returns a java.sql.Time obj - older class
            LocalTime localTime = sqlDate.toLocalTime();    // but Java 8 converts the time to a new LocalTime type
            System.out.println(localTime);  // 02:15
        }

        // - getTimeStamp() = returns date + time
        // ex3: we want to know what date and time elephant Elsa was born  -> in db we have: 2001–05–06 02:15
        ResultSet rs3 = statement.executeQuery("SELECT date_born FROM animal WHERE name = 'Elsa'");
        if (rs3.next()) {
            Timestamp sqlDate = rs3.getTimestamp(1);       // getTimestamp()    - JDBC returns date + time
                                                                        //                  - it returns a java.sql.Timestamp obj - older class
            LocalDateTime localDateTime = sqlDate.toLocalDateTime();    // but Java 8 converts the timeStamp to a new LocalDateTime type
            System.out.println(localDateTime);  // 2001-05-06T02:15
        }

        // ■ JDBC date and time types
        //      JDBC Type       |       java 8 Type         |       Contains
        // ------------------------------------------------------------------------
        //  java.sql.Date       | java.time.LocalDate       |   Date only
        //  java.sql.Time       | java.time.LocalTime       |   Time only
        //  java.sql.TimeStamp  | java.time.LocalDateTime   |   Date + time
        // ------------------------------------------------------------------------

        // ■ getObject() can return any type - for a primitive it uses the wrapper class
        ResultSet rs4 = statement.executeQuery("SELECT id, name FROM species");
        while (rs4.next()) {
            Object idField = rs4.getObject("id");
            Object nameField = rs4.getObject("name");
            if (idField instanceof Integer) {       // you have to confirm that the type is Integer before casting and unboxing it into an int
                int id = (Integer) idField;
                System.out.println(id);
            }
            if (nameField instanceof String) {      // you have to confirm that the type is String before casting and unboxing it into an String
                String name = (String) nameField;
                System.out.println(name);
            }
        }
        // getObject() is not used in real world applications
    }
}
