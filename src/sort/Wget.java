package sort;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;

/**
 * Compilation:  javac Wget.java
 * Execution:    java Wget url
 * Dependencies: In.java Out.java
 * <p>
 * Reads in a URL specified on the command line and saves its contents
 * in a file with the given name.
 * <p>
 * % java Wget http://introcs.cs.princeton.edu/java/data/codes.csv
 */

public class Wget {

    public static void main(String[] args) {
        String url = args[0];
        In in = new In(url);
        String data = in.readAll();

        // write data to a file
        String filename = url.substring(url.lastIndexOf('/') + 1);
        Out out = new Out(filename);
        out.println(data);
        out.close();
    }
}
