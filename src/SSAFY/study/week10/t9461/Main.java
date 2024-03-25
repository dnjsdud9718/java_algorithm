package SSAFY.study.week10.t9461;

import java.util.Scanner;

public class Main {
    static int T, N;
    static long[] dp = new long[101];

    static Scanner sc;
    public static void main(String[] args) {
        sc = new Scanner(System.in);
        T = sc.nextInt();
        dp[0] = dp[1] = dp[2] = 1;
        for (int i = 3; i <= 100; i++) {
            dp[i] = dp[i - 2] + dp[i - 3];
        }
        for (int t = 0; t < T; t++) {
            N = sc.nextInt();
            System.out.println(dp[N - 1]);
        }
        sc.close();
    }
}
