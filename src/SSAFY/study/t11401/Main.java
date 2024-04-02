package SSAFY.study.t11401;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static final int P = 1_000_000_007; // P is prime number
    static int N, K;
    static long[] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        if (K == 0 || N == K) {
            System.out.println(1);
            return;
        }
        // n! dp
        dp = new long[N + 1];
        dp[1] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i] = (dp[i-1] * i) % P;
        }
//        System.out.println(Arrays.toString(dp));
        /* 페르마의 소정리 -> 역원을 구하고 싶다.
                                  1
            nCk % p = (n! *  ---------- ) % p  => ( n! % p * (((n-k)! * k!)^ (p-2)) % p ) % p
                              (n-k)!k!
         */
        System.out.println(dp[N] * pow(dp[N - K] * dp[K] % P, P-2) %P);
        sc.close();
    }

    // Math.pow() -> overflow
    static long pow(long base, long expo) {
        if(expo == 0) return 1;
        else if(expo == 1) return base;

        long num = 1;
        while (expo > 0) {
            if (expo % 2 == 1) {
                num *= base;
                num %= P;
            }
            base = (base * base) % P;
            expo = expo >> 1;
        }
        return num;
    }
}
