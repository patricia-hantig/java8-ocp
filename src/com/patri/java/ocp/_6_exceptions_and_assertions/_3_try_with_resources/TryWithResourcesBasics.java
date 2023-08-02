package com.patri.java.ocp._6_exceptions_and_assertions._3_try_with_resources;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//reading first line from a file and write it into another file
public class TryWithResourcesBasics {

    //prior to Java 7
    public void oldApproach(Path path1, Path path2) throws IOException {
        BufferedReader in = null;
        BufferedWriter out = null;

        try {
            in = Files.newBufferedReader(path1);
            out = Files.newBufferedWriter(path2);
            out.write(in.readLine());
        } /*finally {                       // BAD implementation
            if (in != null) in.close();     // if it throws exception -> next line will not be executed
            if (out != null) out.close();
        }*/ finally {                       // BETTER implementation
            try {
                in.close();
            } catch (IOException e) {
            }
            try {
                out.close();
            } catch (IOException e) {
            }
        }
    }

    //starting with Java 7 we use try-with-resources
    public void newApproach(Path path1, Path path2) throws IOException {
        try (BufferedReader in = Files.newBufferedReader(path1);
             BufferedWriter out = Files.newBufferedWriter(path2)) {  // 'in' and 'out' are only in scope of try ( lines: 30-32)
            out.write(in.readLine());                               // 'in' and 'out' can't be used in catch/finally
        }       // automatically closes all resources open in try (automatic resource management)
        // the finally clause exists implicitly, you just don't have to type it
        // the order for closing resources: reverse order of declaring them ('out' closes before 'in')

        //next clauses are optional
        //try-with-resources can have catch and finally (first runs the implicit finally and then ours)
        catch (IOException e) {
            //catch block
            /*in.readLine();      // 'in' is out of scope DOES NOT COMPILE*/
        } finally {
            //finally block
            /*in.readLine();      // 'in' is out of scope DOES NOT COMPILE*/
        }
    }
}
