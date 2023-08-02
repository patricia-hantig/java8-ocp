package com.patri.java.ocp._10_JDBC._1_introduction_relational_db_and_sql;

public class Introduction {
    public static void main(String[] args) {
        // ■■■■ JDBC = Java Database Connectivity

        // ■ Data       = is information
        // ■ Database   = is an organized collection of data
        // ■ Relational database = is a database that is organized into tables (rows and columns)

        // ■■ There are 2 ways to access a relational database from Java:
        // 1. JDBC API (Java Database Connectivity Language)
        //      - accesses data as rows and columns
        // 2. JPA API (Java Persistence API)
        //      - accesses data through Java objects using ORM (object-relational mapping)
        //      - the data is received in Java objects

        // - a relational database is accessed through SQL (Structured Query Language)
        // - there is another type of database called NoSQL -> data is stored in other format than tables

        // JDK comes automatically with an open source database: Derby
        // you could also install MySQL or PostgreSQL stand-alone databases
    }
}
