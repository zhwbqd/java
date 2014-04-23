package arithemtic.recursion;

import java.lang.String;import java.lang.System;public class Fibonacci {
    public static void main(String args[]) {
        for (int i = 0; i <8 ; i++) {
            System.out.println(fibonacci(i));
        }

    }

    static int fibonacci(int n) {
        if (n < 1)
            return 0;
        else if (n == 1) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}