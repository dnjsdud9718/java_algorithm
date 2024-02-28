package SSAFY.algo.baekjoon.t1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    시간 초과
    -> 해결 -> 파스칼 삼각형 -> 메모이제이션
    nCr = n-1Cr-1 + n-1Cr
 */
public class Main2 {
    static int N, K, COUNT;
    static int[][] dp;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        dp = new int[31][31];
        for (int i = 0; i < 31; i++) {
            dp[i][0] = dp[i][i] = 1;
        }
        for (int i = 1; i < 31; i++) {
            for (int j = 1; j < i; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
//            System.out.println(Arrays.toString(dp[i]));
        }


        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            if (N < K) {
                int i = N;
                N = K;
                K = i;
            }
            sb.append(dp[N][K]).append('\n');

        }
        System.out.println(sb);
        br.close();
    }
}
