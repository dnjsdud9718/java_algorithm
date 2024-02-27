package SSAFY.study.week6.t1149;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main3 {
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
            Arrays.fill(dp[i], -1);
        }
        answer = INF;
        answer = Math.min(answer, logic(0, 0));
        answer = Math.min(answer, logic(0, 1));
        answer = Math.min(answer, logic(0, 2));
        System.out.println(answer);

        br.close();
    }

    public static int logic(int idx, int color) {
        if (idx == N - 1) {
            return src[idx][color];
        }
        if(dp[idx][color] != -1) return dp[idx][color];
        dp[idx][color] = INF;
        dp[idx][color] = Math.min(dp[idx][color], src[idx][color] + logic(idx + 1, (color + 1) % 3));
        dp[idx][color] = Math.min(dp[idx][color], src[idx][color] + logic(idx + 1, (color + 2) % 3));
        return dp[idx][color];
    }
}
