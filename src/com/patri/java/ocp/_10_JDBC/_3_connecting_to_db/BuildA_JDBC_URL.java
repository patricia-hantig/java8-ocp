package com.patri.java.ocp._10_JDBC._3_connecting_to_db;

public class BuildA_JDBC_URL {
    public static void main(String[] args) {

        // the first step in doing anything with a database is connecting to it
        // 1. How to build a JDBC URL
        // 2. How to get a connection to the database

        // ■■■ Building a JDBC URL:

        // in order to access a website -> you need to know the URL of the website
        // in order to access your email -> you need to know your username and password
        // => with JDBC is the same -> to access a database -> you need to know this information

        // JDBC URL has 3 parts:
        //            jdbc          :       postgres        :   //localhost:5432/zoo
        //          protocol            product/vendor name     database specific details
        // this is always like this     ex: derby, mysql        localhost or IP address - location, 5432 - DB port, zoo - DB name

        // ex of JDBC URL:
        // jdbc:postgres://localhost:5432/zoo
        // jdbc:derby:zoo
        // jdbc:postgresql://localhost/zoo
        // jdbc:oracle:thin:@123.123.123.123:1521:zoo
        // jdbc:mysql://localhost:3306/zoo?profileSQL=true

        // wrong ex of JDBC URL:
        // jdbc:postgresql://local/zoo      => 'local' instead of 'localhost'
        // jdbc:mysql://12345/zoo           => location can't be '12345' -> must be localhost or an IP address
        // jdbc;oracle;thin;/localhost/zoo  => uses ';' instead of ':'
    }
}
