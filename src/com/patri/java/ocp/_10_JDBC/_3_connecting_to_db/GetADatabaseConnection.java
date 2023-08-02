package com.patri.java.ocp._10_JDBC._3_connecting_to_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetADatabaseConnection {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        // ■■■ Getting a Database Connection:
        // there are 2 main ways to get a Connection: DriverManager or DataSource

        // ■ DriverManager - it's not used in productive code
        // - is a class in JDK
        // - it uses the factory pattern -> you call a static method to get a Connection
        // - the factory pattern = you can get any implementation of the interface when calling the method
        // - the called method is: getConnection()

        // ■ DataSource - it's used in applications
        // - is a factory that has more features than DriverManager
        // - it can pool connections or store the database connection info outside the application

        // ■■ 1. Using DriverManager
        // ex for derby db:
        Connection connection = DriverManager.getConnection("jdbc:derby:zoo");
        System.out.println(connection);

        // running this -> you could get an SQLException -> in this case the jar for the driver was not added in the classpath
        // Exception in thread "main" java.sql.SQLException: No suitable driver found for jdbc:derby:zoo
        // at java.sql.DriverManager.getConnection(DriverManager.java:689)
        // at java.sql.DriverManager.getConnection(DriverManager.java:270)

        // if it runs successfully -> you get something like this:
        // org.apache.derby.impl.jdbc.EmbedConnection@1159114532 (XID = 207), (SESSIONID = 1), (DATABASE = zoo), (DRDAID = null)
        // as you can see is not Connection -> is an implementation of Connection: EmbedConnection

        // ex for postgres db which uses a username and password:
        /*Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/ocp-book",
                "username",
                "password"
        );
        System.out.println(conn);
        // if you include the driver and write the right username and password, you should get something like this:
        // org.postgresql.jdbc4.Jdbc4Connection@eed1f14
        */

        // the nice thing about factory is that it takes care of the logic of creating a class for you
        // -> you don't need to know the name of the class that implements Connection
        // - What happens inside:   DriverManager class looks through the classpath for JARs that contain a Driver
        //                          DriverManager knows that a JAR is a driver because it contains a file called java.sql.Driver in the directory META-INF/services
        //                          DriverManager then looks through any drivers it can find to see if they can handle the JDBC URL
        //                          If so, it creates a Connection using that Driver. If not, it gives up and throws a SQLException.


        // ■■ 2. Using DataSource
        // - is used in Real World Scenario
        // - one reason is that you don't need to know the database password
        // - another reason is that a DataSource maintains a connection pool so that you can keep reusing the same connection
        // rather than needing to get a new one each time


        // ■ Class.forName()
        // in older code we use it before getting a Connection
        // Class.forName() - loads a class -> this lets DriverManager use a Driver, even if the JAR doesn't have a META-INF/service/java.sql.Driver file
        // - when Class.forName() is used - this could throw a ClassNotFoundException
        // ex:
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        Connection conn = DriverManager.getConnection("jdbc:derby:zoo");
        System.out.println(conn);

        // ex2:
        // Class.forName("not.a.driver");
        // the above ex throws ClassNotFoundException
        // Exception in thread "main" java.lang.ClassNotFoundException: not.a.driver

        // ■ JDBC 3.0 vs. JDBC 4.0 drivers:
        // - starting with JDBC 4.0 in Java 6 -> META-INF/services/java.sql.Driver became mandatory to be inside the JAR
        // - before it some drivers included it and some didn't

        //                                              | JDBC <= 3.0 Driver    |   JDBC >= 4.0 Driver
        // ------------------------------------------------------------------------------------------
        // Required to contain java.sql.Driver          |           NO          |           YES
        // Java will use java.sql.Driver file if present|           YES         |           YES
        // Required to use Class.forName()              |           YES         |           NO
        // Allowed to use Class.forName()               |           YES         |           YES
        // ------------------------------------------------------------------------------------------

    }
}
