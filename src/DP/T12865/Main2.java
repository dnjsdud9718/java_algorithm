package DP.T12865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    배낭 문제 DP의 K가 클 경우 메모리초과가 날것이다.
    어떻게 해결할 수 있을까?
    N개의 아이템을 무게 K만큼 담을 수 있는 배낭에 담아서 얻을 수 있는 최대 가치
    무게 고정, 가치 최대 <=> 가치 고정, 무게 최소
 */
public class Main2 {
    static final int INF = 987_654_321;
    static int N, W, vSum;
    static int[] iWeight;
    static int[] iValue;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        iWeight = new int[N + 1];
        iValue = new int[N + 1];
        vSum = 0;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            iWeight[i] = w;
            iValue[i] = v;
            vSum += v;
        }
        dp = new int[N + 1][vSum + 1];
        for (int i = 0; i <= N; i++) Arrays.fill(dp[i], INF);
        dp[0][0] = 0;
        for (int i = 1; i <= N; i++) {
            for (int v = 0; v <= vSum; v++) {
                if (v >= iValue[i]) {
                    dp[i][v] = Math.min(dp[i-1][v], dp[i - 1][v-iValue[i]] + iWeight[i]);
                } else dp[i][v] = dp[i - 1][v];
            }
        }
        int res = 0;
        for (int i = 0; i <= vSum; i++) {
            if(dp[N][i] <= W) res = i;
        }
//        System.out.println(Arrays.toString(dp[N]));
        System.out.println(res);
        br.close();
    }
}
