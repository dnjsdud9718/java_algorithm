package SSAFY.B형.week3.최장공통부분수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
    LCS baekjoon 9251 최장 공통 부분 수열
 */
public class Main {
    static char[] c1;
    static char[] c2;
    static int l1, l2;
    static int[][]dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        c1 = br.readLine().toCharArray();
        c2 = br.readLine().toCharArray();
        l1 = c1.length;
        l2 = c2.length;
        dp = new int[l1 + 1][l2 + 1];
        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                if (c1[i - 1] == c2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        System.out.println(dp[l1][l2]);

        br.close();
    }
}
