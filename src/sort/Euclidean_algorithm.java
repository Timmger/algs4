package sort;

import java.util.Scanner;

public class Euclidean_algorithm {

    public static int gcd(int a, int b) {
        if (a == 0) {
            return b;
        }else{
            while(b != 0){
                int r = a % b;
                a = b;
                b = r;
                gcd(a, b);
            }
            return a;
        }
    }

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入整数a：");
        int a = sc.nextInt();
        System.out.println("请输入整数b：");
        int b = sc.nextInt();
        int gcd  = gcd(a, b);
        System.out.format("%d 和 %d 的最大公约数为：%d\n", a, b, gcd);
        System.out.println(gcd);
    }
}
