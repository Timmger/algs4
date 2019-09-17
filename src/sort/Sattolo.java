package sort;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/****************************
 * Compilation: javac Sattolo.java
 * Execution: java Sattolo < list.txt
 * Dependencies: StdIn.java StdOut.java
 * Data files: https://algs4.cs.princeton.edu/11model/cards.txt
 *
 * 假定Math.random()在（0，1）间生成独立均匀分布数，
 * 使用Sattolo算法从中读取并打印一个均匀随机循环
 *
 * **************************
 */

public class Sattolo {

    // 这个类不应该被实例化，使用private修饰构造函数
    private Sattolo() {
    }

    /**
     * 重新排列对象数组使其成为一个均匀随机循环
     *
     * @param a the array to be arranged
     * @see edu.princeton.cs.algs4.StdRandom
     */
    public static void cycle(Object[] a) {
        int n = a.length;
        for (int i = n; i > 1; i--) {
            // 从[0, i-1)中均匀选取索引
            int r = (int) (Math.random() * (i - 1)); // Math.random()返回0和1之间的随机double值
            // 交换a[r]和a[i-1]
            Object swap = a[r];
            a[r] = a[i - 1];
            a[i - 1] = swap;
        }
    }
    /**
     * 从标准输入读取一串Strings,打乱并输出结果
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        // 读取数据
        String[] a = StdIn.readAllStrings();

        // 打乱数组
        Sattolo.cycle(a);

        // 打印输出
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }
}
