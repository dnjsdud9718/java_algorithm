package DP.t10422;

import java.util.Scanner;

/**
 * 백준 10422 괄호
 * 메모리 최적화 시도 : 홀수 무시
 */
public class Main2 {
    static final long DIV = 1_000_000_007L;
    static int T, N;
    static long[] dp = new long[2501];
    public static void main(String[] args) {
        dp[0] = 1;
//        dp[1] = 1;
        for (int i = 1; i < dp.length; i++) {
            for(int j=0; j< i; j++){
//                dp[i] += dp[j] * dp[i - 2 - j];
                dp[i] = (dp[i]) % DIV + (dp[j]%DIV * dp[i - 1 - j]%DIV) % DIV;
            }
        }
//        for (int i = 1; i <= 500; i++) {
//            System.out.print("i = " + i*2 + ", val = " + dp[i] % DIV);
//            System.out.println();
//        }
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        for (int t = 1; t <= T; t++) {
            N = sc.nextInt();
            if(N==1 || N%2 != 0) System.out.println(0);
            else System.out.println(dp[N/2]%DIV);

        }
        sc.close();
    }

}
