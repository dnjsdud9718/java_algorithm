package SSAFY.study.week11.t27980;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, max;
    static int[][] dp;
    static char[] board;
    static char[] myStr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[M + 2][N + 2];
        board = new char[N + 1];
        String s = br.readLine();
        for (int i = 1; i <= N; i++) board[i] = s.charAt(i - 1);
        myStr = new char[M + 1];
        s = br.readLine();
        for (int i = 1; i <= M; i++) myStr[i] = s.charAt(i - 1);
//
//        System.out.println(Arrays.toString(board));
//        System.out.println(Arrays.toString(myStr));

        for (int i = 1; i <= M; i++) { // 내가 읽어야할 문자
            for (int j = 1; j <= N; j++) { // 내가 서 있는 위치
                dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j + 1]);
                if(board[j] == myStr[i]) dp[i][j]++;
            }
            if (i == M) {
                max = 0;
                for (int k = 1; k <= N; k++) {
                    max = Math.max(max, dp[i][k]);
                }

            }
        }
        System.out.println(max);
        br.close();
    }
}
