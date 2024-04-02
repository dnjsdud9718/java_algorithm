package SSAFY.study.week11.t14983;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 987_654_321;
    static int N, M, R;
    static int[] items;
    static int[][] dp;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 지역 수
        M = Integer.parseInt(st.nextToken()); // 수색범위
        R = Integer.parseInt(st.nextToken()); // 길의 개수

        items = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) items[i] = Integer.parseInt(st.nextToken());

        dp = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dp[i], INF);
            dp[i][i] = 0;
        }

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            dp[u][v] = dp[v][u] = d;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }
//        for (int i = 1; i <= N; i++) {
//            System.out.println(Arrays.toString(dp[i]));
//        }

        answer = 0;
        for (int i = 1; i <= N; i++) {
            int max = 0;
            for (int j = 1; j <= N; j++) {
                if(dp[i][j] <= M ) max += items[j];
            }
            answer = Math.max(answer, max);
        }
        System.out.println(answer);
        br.close();
    }
}
