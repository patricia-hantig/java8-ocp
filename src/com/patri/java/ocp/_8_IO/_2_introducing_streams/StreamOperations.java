package com.patri.java.ocp._8_IO._2_introducing_streams;

import java.io.*;

public class StreamOperations {
    public static void main(String[] args) {

        // ■■■ Closing the Stream
        // - Streams are resources and this means they need to be closed after they are used otherwise this could lead to resource leaks
        // - we could use method 'close()' in the 'finally' block or we could use try-with-resources
        // - if a file is not close properly could cause that no other process could read/write until the program is terminated
        // - best practice: close the stream resources by using the try-with-resources syntax


        // ■■■ Flushing the Stream
        // - when data is written to an OutputStream - data will not make it to the file immediately
        // - the data may be cached in memory -> so when the application terminates unexpectedly => the data in cache would be lost (because it was never written to the file)
        // - Java gives us 'flush()' method - which writes all accumulated data immediately to disk
        // - flush() reduces the amount of data lost if the application terminates unexpectedly
        // - !!! each time flush() is used -> cause a delay in application - especially for large files
        // - flush() should be used intermittently - it shouldn't be used after each write and also it shouldn't be used at the end
        // because close() will automatically do this


        // ■■■ Marking the Stream
        // - 'InputStream' and 'Reader' include: 'mark(int)' and 'reset()' - to move the stream back to an earlier position
        // - before calling these methods - you should call markSupported() - which returns 'true' only if 'mark()' is supported
        // - not all stream classes support this operation -> you will get an exception if a class does not support

        // - if you want to go back to the earlier position where you last called mark() - just call reset()
        // mark() - marks the position where you want to come back

        // ex: we have a FileReader instance whose next values are ABCD
        // we could use an InputStream but since it's byte stream it's a bit more difficult to implement this
        try (
                BufferedReader bufferedReader = new BufferedReader(
                        new FileReader("C:\\Users\\Patry\\IdeaProjects\\OCA_and_OCP\\src\\com\\patri\\java\\ocp\\_8_IO\\_2_introducing_streams\\mark.txt"))) {
            System.out.print((char)bufferedReader.read());          // A
            if (bufferedReader.markSupported()) {
                bufferedReader.mark(100);
                System.out.print((char)bufferedReader.read());      // B
                System.out.print((char)bufferedReader.read());      // C
                bufferedReader.reset();
            }
            System.out.print((char)bufferedReader.read());          // B
            System.out.print((char)bufferedReader.read());          // C
            System.out.print((char)bufferedReader.read());          // D
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // - if you call reset() after you have passed your mark() read limit, an exception may be thrown at runtime since the marked position may become invalidated


        // ■■■ Skipping over Data
        // - 'InputStream' and 'Reader' include: 'skip()' method - which skips over a certain number of bytes
        // - skip() returns a long value which indicates the number of bytes that were actually skipped
        // - if skip() returns 0 or negative => the end of the stream was reached

        // ex: we have a FileReader instance whose next values are TIGERS
        System.out.println();
        try (
                BufferedReader bufferedReader = new BufferedReader(
                        new FileReader("C:\\Users\\Patry\\IdeaProjects\\OCA_and_OCP\\src\\com\\patri\\java\\ocp\\_8_IO\\_2_introducing_streams\\tigers.txt"))) {
            System.out.print((char)bufferedReader.read());          // T
            bufferedReader.skip(2);                             // skip IG
            bufferedReader.read();                                  // E - is not printed
            System.out.print((char)bufferedReader.read());          // R
            System.out.print((char)bufferedReader.read());          // S
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // calling skip() is equivalent to calling read() and discard the output (but using skip() is much faster)
    }
}
