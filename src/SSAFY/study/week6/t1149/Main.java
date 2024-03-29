package SSAFY.study.week6.t1149;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 987_654_321;
    static int N, answer;
    static int[][] src;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        src = new int[N][3];
        dp = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                src[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < 3; i++) dp[0][i] = src[0][i];
        for (int i = 1; i < N; i++) {
            for (int color = 0; color < 3; color++) {
                dp[i][color] = src[i][color] + Math.min(dp[i - 1][(color + 1) % 3], dp[i - 1][(color + 2) % 3]);
            }
        }
        answer = INF;
        for (int i = 0; i < 3; i++) {
            answer = Math.min(answer, dp[N - 1][i]);
        }
        System.out.println(answer);
        br.close();
    }
}
