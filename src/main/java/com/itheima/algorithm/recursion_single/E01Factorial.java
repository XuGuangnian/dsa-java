package com.itheima.algorithm.recursion_single;

/**
 * 递归求阶乘
 */
public class E01Factorial {

    public static int f(int n) {
        if (n == 1 || n == 0) {
            return 1;
        }
        return n * f(n - 1);
    }

    public static void main(String[] args) {
        int f = f(5);
        int f2 = f(0);
        System.out.println(f);
        System.out.println(f2);
    }
}
