package com.patri.java.ocp._8_IO._3_working_with_streams;

import java.io.*;

public class FileInputStream_FileOutputStream_Classes {

    // ■■■ FileInputStream & FileOutputStream Classes
    // - they are used to read/write BYTES from a file
    // - these classes have constructors that take a File object or String - representing the path to the file

    // ■ FileInputStream - the data is accessed by successive calls to the read() method until method returns -1 (end of the stream - end of file was reached)
    //          - read() returns a primitive int value, NOT byte values
    //          - also contains overloaded versions for read() - ex method which take a pointer to a byte array where the data is written and returns
    //          an integer value indicating how many bytes can be read into the byte array - can be used by Buffered classes
    // ■ FileOutputStream - the data is accessed by writing successive bytes using write(int) method
    //          - write() uses int instead of byte for writing a single byte to a file
    //          - also contains overloaded versions for write() - ex method that allow a byte array to be passed and can be used by Buffered classes

}

// ex: FileInputStream & FileOutputStream to copy a file
class CopyFileSample {

    public static void copy(File source, File destination) throws IOException {
        try (InputStream inputStream = new FileInputStream(source);
             OutputStream outputStream = new FileOutputStream(destination)) {
            int b;
            while ((b = inputStream.read()) != -1){
                outputStream.write(b);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        File source = new File("C:\\Users\\Patry\\IdeaProjects\\OCA_and_OCP\\src\\com\\patri\\java\\ocp\\_8_IO\\_3_working_with_streams\\Zoo.java");
        File destination = new File("C:\\Users\\Patry\\IdeaProjects\\OCA_and_OCP\\src\\com\\patri\\java\\ocp\\_8_IO\\_3_working_with_streams\\zoo\\Zoo.java");
        copy(source, destination);
        // if the destination file already exists - it will be overridden
        // the performance for this code, especially for large files, would not be particularly good because the sample does not use any byte arrays
    }
}

// ■■■ BufferedInputStream & BufferedOutputStream Classes
// ■ BufferedInputStream - the data is not read one byte at a time
//                      - we use the method read(byte[]) - which returns the number of bytes read into a byte array
//                      - if read(byte[]) returns 0 -> we have reached the end of the file
//                      - we can read an particular number of bytes at a time
//                      - ex: consider that the file has 1054 bytes and the buffer array size is 1024 => we read 1024 bytes in the first read and then 30 bytes in the last read
// ■ BufferedOutputStream - the data is written using a byte array
//                      - we use the method write(byte[], int, int) - write(byte[], int offsetValue, int lengthValue)
//                      byte array, an offset value (is the number of values to skip before writing characters, and it is often set to 0)
//                      and length value (is the number of characters from the byte array to write)

// ■ why use Buffered Classes?
// in practice it's common to use Buffered classes - because they contain performance enhancements for managing stream data in memory
// for ex: BufferedInputStream class is capable of retrieving and storing in memory more data than you might request with a single read() call.

// ex: BufferedInputStream & BufferedOutputStream to copy a file
class CopyBufferFileSample {
    public static void copy(File source, File destination) throws IOException {
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(source));
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(destination))) {
            byte[] buffer = new byte[1024];
            int lengthRead;
            while ((lengthRead = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, lengthRead);
                outputStream.flush();
            }
        }
    }
    // performance improvement for using both the Buffered classes and byte arrays
    // we also added flush() - to ensure the written data makes it to the disk before the next buffer of data is read

    // buffer size - in our ex is 1024 - choosing it depends on CPU and file system block size
    // regardless of the buffer size you choose -> the biggest performance increase is from moving non-buffered to buffered file access
}