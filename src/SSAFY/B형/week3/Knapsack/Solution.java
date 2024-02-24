package SSAFY.B형.week3.Knapsack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int N, K;
    static int[][] item;
    static int[][] dp;
    static final int INF = 987_654_321;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            item = new int[N + 1][2];
            dp = new int[N + 1][K + 1];
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                item[i][0] = v;
                item[i][1] = c;
            }
            for (int i = 1; i <= N; i++) { // item 번호
                for (int k = 1; k <= K; k++) { // 무게
                    if (item[i][0] > k) dp[i][k] = dp[i-1][k];
                    else dp[i][k] = Math.max(dp[i - 1][k], dp[i - 1][k - item[i][0]]+item[i][1]);
                }
            }
            sb.append('#').append(t).append(' ').append(dp[N][K]).append('\n');
        }
        System.out.println(sb);
        br.close();
    }
}
