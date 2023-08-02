package com.patri.java.ocp._10_JDBC._1_introduction_relational_db_and_sql;

public class BasicSQLStatements {
    public static void main(String[] args) {
        // ■■■ 4 types of statements for working with the data in tables:
        // (CRUD = Create, Remove, Update, Delete)
        // ■ INSERT - add a new row to the table
        // ■ SELECT - retrieve data from the table
        // ■ UPDATE - change 0 or more rows in the table
        // ■ DELETE - remove 0 or more rows in the table

        // SQL keywords are case insensitive => select = SELECT = Select (all are equivalent)
        // VARCHAR = 'Variable character' = String (in Java)

        // - INSERT is usually used to create a new row in a table
        // ex:
        //      INSERT INTO species VALUES (3, 'Asian Elephant', 7.5)
        // - String data is written between ''

        // - SELECT is usually used to read data from the table
        // ex1:
        //      SELECT * FROM species WHERE id = 3
        // - where clause is optional
        // * = means all
        // ex2: -> you could list out the columns that you want to return
        //      SELECT name, num_acres FROM species WHERE id = 3
        // ex3: -> you can get only information about the whole result without returning individual rows
        //      SELECT COUNT(*), SUM(num_acres) FROM species
        // - this tells us the number of species, how much space we need fro them

        // - UPDATE is usually used to modify rows in the database
        // ex:
        //      UPDATE species SET num_acres = num_acres + .5 WHERE name = "Asian Elephant'
        // - where clause is optional -> if it's omitted all rows will be updated
        // UPDATE always specifies the table to update and the column to update

        // - DELETE is usually used to remove one or more rows in the database
        // ex:
        //      DELETE FROM species WHERE name = 'Asian Elephant'
        // - where clause is optional -> if it's omitted the entire table will be emptied
    }
}
