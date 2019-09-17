package sort;

import edu.princeton.cs.algs4.StdDraw;

public class Draw01 {
    public static void draw(int N) {
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, N*N);
        StdDraw.setPenRadius(.01);
        for(int i = 1; i <= N; i++){
            StdDraw.point(i, i);
            StdDraw.point(i, i*i);
            StdDraw.point(i, i*Math.log(i));
        }
    }

    public static void main(String[] args) {
        Draw01.draw(100);
    }
}
