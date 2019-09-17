package sort;

import edu.princeton.cs.algs4.StdDraw;

/******************************************************************************
 *  Compilation:  javac BouncingBall.java
 *  Execution:    java BouncingBall
 *  Dependencies: StdDraw.java
 *
 *  Implementation of a 2-d bouncing ball in the box from (-1, -1) to (1, 1).
 *
 *  % java BouncingBall
 *
 ******************************************************************************/

public class BouncingBall {
    public static void main(String[] args) {
        // set the scale of the coordinate system
        StdDraw.setXscale(-1.0, 1.0);
        StdDraw.setYscale(-1.0, 1.0);
        StdDraw.enableDoubleBuffering();

        // initial values
        double rx = 0.480, ry = 0.860; // 位置
        double vx = 0.015, vy = 0.023; // 速度
        double radius = 0.05; // 半径

        // 主要动画循环
        while(true) {
            // 根据弹性定律在墙面反弹
            if(Math.abs(rx + vx) > 1.0 - radius)
                vx = -vx;
            if(Math.abs(ry + vy) > 1.0 - radius)
                vy = -vy;

            // 更新位置
            rx = rx + vx;
            ry = ry + vy;

            // 清理背景
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.filledSquare(0, 0, 1.0);

            // 在屏幕上画球
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledCircle(rx, ry, radius);

            // 显示并暂停20ms
            StdDraw.show();
            StdDraw.pause(20);
        }

    }
}
