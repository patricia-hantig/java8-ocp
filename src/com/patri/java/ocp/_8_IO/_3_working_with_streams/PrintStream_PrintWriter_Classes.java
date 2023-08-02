package com.patri.java.ocp._8_IO._3_working_with_streams;

import java.io.*;

public class PrintStream_PrintWriter_Classes {
    public static void main(String[] args) throws FileNotFoundException {
        // ■■■ PrintStream and PrintWriter
        // - they are high-level streams that write representation of Java objects to a text-based output stream
        // ■ PrintStream - OutputStream -> writes data as bytes
        // ■ PrintWriter - Writer -> writes data as characters

        // - System.out & System.error -> are PrintStream objects
        // - because PrintStream inherits OutputStream and PrintWriter inherits from Writer => both have the method 'write()'
        // - methods for PrintStream & PrintWriter: write(), print(), println(), format() and printf()
        // - write() -> throws checked exception IOException
        // print(), println(), format() and printf() -> do not throw any checked exceptions
        // - both classes have the method: checkError() - used to detect the presence of a problem after trying to write data to the stream

        // - In the next examples we will use PrintWriter -> but the same examples could be rewritten using PrintStream

        // ■ print() - equivalent with String.valueOf()
        PrintWriter out = new PrintWriter("src\\com\\patri\\java\\ocp\\_8_IO\\_3_working_with_streams\\zooFromPrinter.txt");
        out.print(5);                   // PrintWriter method
        out.write(String.valueOf(5));   // Writer method

        out.print(2.0);                 // PrintWriter method
        out.write(String.valueOf(2.0)); // Writer method

        Animal_ animal = new Animal_();
        out.print(animal);              // PrintWriter method
        out.write(animal == null ? "null" : animal.toString()); // Writer method

        // valueOf() applied to an object calls the object’s toString() method or returns null if the object is not set

        // ■ println() - identical with print() -> they insert a line break after the String value is written
        // we have also a version of println() with no arguments that terminates the current line by writing a line separator
        // 'line.separator' - property in Java:
        System.getProperty("line.separator");

        // ■ format() & printf()
        // - format() takes a String, an optional locale and a set of arguments and writes a formatted String to the stream
        // - printf() similar to format()
        // we have:
        // public PrintWriter format(String format, Object args...)
        // public PrintWriter printf(String format, Object args...)
    }
}

// ■■■ Sample PrintWriter Application
class SamplePrintWriterApp {
    public static void main(String[] args) throws IOException {
        File source = new File("src\\com\\patri\\java\\ocp\\_8_IO\\_3_working_with_streams\\zoo.log");
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(source)))) {
            out.print("Today's weather is: ");
            out.println("Sunny");
            out.print("Today's temperature at the zoo is: ");
            out.print(1/3.0);
            out.println('C');
            out.format("It has rained 10.12 inches this year");
            out.println();
            out.print("It may rain 21.2 more inches this year");
        }
    }
}
