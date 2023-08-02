package com.patri.java.ocp._8_IO._3_working_with_streams;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class FileReader_FileWriter_Classes {

    // ■■■ FileReader & FileWriter
    // - they are used to read/write TEXT DATA (CHAR VALUES) from a file
    // - they are among most common ways for developers to interact with files
    // - they contain read() and write() methods - these methods read/write char values instead of byte values
    // - read() uses a primitive int value and return -1 (end of the stream - end of file was reached)
    // - write() method -> writes a String object directly to the stream
    // - they also contain other methods: close(), flush()

    // ■■■ BufferedReader & BufferedWriter Classes
    // - using them the code has much better performance
    // - they contain readLine() and write(String) methods
    // - read/write groups of character/String data
}

// ex: BufferedReader & BufferedWriter for reading a text file, outputting each line to screen and writing a copy of the file to disk
class CopyTextFileExample {
    public static List<String> readFile(File source) throws IOException {
        List<String> data = new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            String s;   // we use a temporary String reference s to hold the value of the data in loop as we read it
            while ((s = reader.readLine()) != null) {   // returns null if it reached the end of the file
                // unlike FileInputStream and FileReader, where we used -1 to check for file termination of an int value,
                // with BufferedReader, we stop reading the file when readLine() returns null
                data.add(s);
            }
        }
        return data;
    }
    // By working entirely with String values instead of byte values, we have access to the all of the methods in the String API to manipulate data

    public static void writeFile(List<String> data, File destination) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(destination))) {
            for (String s: data) {
                writer.write(s);
                writer.newLine();
            }
            // Unlike the previous examples where we had to write the code one byte at a time or by using a byte array,
            // we can write the entire String in a single call
        }
    }

    public static void main(String[] args) throws IOException {
        File source = new File("C:\\Users\\Patry\\IdeaProjects\\OCA_and_OCP\\src\\com\\patri\\java\\ocp\\_8_IO\\_3_working_with_streams\\zoo.txt");
        File destination = new File("C:\\Users\\Patry\\IdeaProjects\\OCA_and_OCP\\src\\com\\patri\\java\\ocp\\_8_IO\\_3_working_with_streams\\zooCopy.txt");

        List<String> data = readFile(source);
        for (String record: data) {
            System.out.println(record);
        }
        writeFile(data, destination);
    }
}

// ■ Comparing BufferedReader & BufferedWriter copy application and InputStream & OutputStream copy application
// - only the solution with Reader/Writer gives us access to the text data
// - with Reader/Writer we don't have to detect and process the character encoding
// The character encoding = determines how characters are encoded and stored in bytes and later read back or decoded as characters
// Java supports many character encodings: it uses one byte for Latin characters - UTF-8 and ASCII for example, and it's using two or more bytes per character - UTF-16

// although you can use InputStream/OutputStream instead of Reader/Writer to read and write text files, it is inappropriate to do so

// ■ Character encoding in Java
// the character encoding can be specified using the Charset class by passing a name value to the static Charset.forName() method
// ex:
class CharacterEncoding {
    public static void main(String[] args) {
        Charset usAsciiCharset = Charset.forName("US-ASCII");
        Charset utf8Charset = Charset.forName("UTF-8");
        Charset utf16Charset = Charset.forName("UTF-16");
    }
    // Java supports numerous character encodings, each specified by a different standard name value.
}
