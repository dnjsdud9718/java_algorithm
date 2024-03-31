package DP.t2293;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] coins;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        coins = new int[N+1];
        for (int i = 1; i <= N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(coins);
        dp = new int[N + 1][K + 1];
        for(int i=1; i<=N; i++) dp[i][0] = 1;
        for (int i = 1; i <= N; i++) {
            int coin = coins[i];
            for (int k = 1; k <= K; k++) {
                if(coin > k) dp[i][k] = dp[i - 1][k]; // coin을 추가해서 경우를 추가할 수 없다.
                else dp[i][k] = dp[i][k - coin] + dp[i - 1][k]; // coin을 추가한 경우 + 추가하지 않은 경우
            }
        }
//        for (int i = 0; i <= N; i++) {
//            System.out.println(Arrays.toString(dp[i]));
//        }
        System.out.println(dp[N][K]);
        br.close();
    }
}
