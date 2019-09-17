package sort;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class Draw02 {
    public static void draw(int N) {
        double[] a = new double[N];
        for(int i = 0; i < N; i++) {
            a[i] = StdRandom.random();
        }
        Arrays.sort(a);
        for(int i = 0; i < N; i++) {
            double x = 1.0*i/N;
            double y = a[i]/2.0;
            double rw = 0.5/N;
            double rh = a[i]/2.0;
            StdDraw.filledRectangle(x, y, rw, rh);
        }

    }
    public static void main(String[] args) {
        Draw02.draw(50);
    }
}
