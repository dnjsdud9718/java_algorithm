package DP.t10422;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 백준 10422 괄호
 */
public class Main {
    static final long DIV = 1_000_000_007L;
    static int T, N;
    static long[] dp = new long[5001];
    public static void main(String[] args) {
        dp[0] = 1;
        dp[2] = 1;
        for (int i = 4; i < dp.length; i++) {
            for(int j=0; j<= i-2; j+=2){
//                dp[i] += dp[j] * dp[i - 2 - j];
                dp[i] = (dp[i]) % DIV + (dp[j]%DIV * dp[i - 2 - j]%DIV) % DIV;
            }
        }
//        for (int i = 2; i <= 500; i += 2) {
//            System.out.print("i = " + i + ", val = " + dp[i] % DIV);
//            System.out.println();
//        }
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        for (int t = 1; t <= T; t++) {
            N = sc.nextInt();
            System.out.println(dp[N]%DIV);

        }
        sc.close();
    }

}
