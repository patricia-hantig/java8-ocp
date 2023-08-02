package com.patri.java.ocp._10_JDBC._1_introduction_relational_db_and_sql;

public class StructureOfARelationalDatabase {
    public static void main(String[] args) {
        // Example of 2 tables in our relational database:

        // table species:
        //                                                  column
        //      id      |           name            |   num_acres
        //    integer   |   character varying(255)  |    numeric
        // ----------------------------------------------------------
        //      1       |   African Elephant        |   7.5             row
        //      2       |   Zebra                   |   1.2

        // table animal:
        //      id      |   species_id  |           name            |           date_born
        //    integer   |     integer   |   character varying(255)  |timestamp without time zone
        // ---------------------------------------------------------------------------------------
        //      1       |       1       |   Elsa                    |  2001-05-06 02:15:00
        //      2       |       2       |   Zelda                   |  2002-08-15 09:12:00
        //      3       |       1       |   Ester                   |  2002-09-09 10:36:00
        //      4       |       1       |   Eddie                   |  2010-06-08 01:24:00
        //      5       |       2       |   Zoe                     |  2005-11-12 03:44:00

        // - these 2 relate to each other because an animal belongs to a species -> relational database
        // - each table has a primary key = which gives us a unique way to reference each row
        // primary key for table species: column id
        // primary key for table animal: column id

        // ■■■ Set Up the Database (Derby): -> SetupDerbyDatabase.java
        // DDL = Database definition language
        // DML = Database manipulation language
    }
}
