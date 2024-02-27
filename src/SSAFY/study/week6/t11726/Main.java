package SSAFY.study.week6.t11726;

import java.util.Scanner;

/*
    11726 2*N타일 -> 피보나치 수열

 */
public class Main {
    static int N;
    static final int DIVISOR = 10_007;
    static int[] fib;
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        N = sc.nextInt();
        fib = new int[N + 1];
        fib[0] = fib[1] = 1;
        for (int i = 2; i <= N; i++) {
            fib[i] = fib[i-2]%DIVISOR+fib[i-1]%DIVISOR;
        }
        System.out.println(fib[N]%DIVISOR);
    }

}
