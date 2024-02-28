package SSAFY.algo.baekjoon.t17070;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {
    static int N;
    static int[][] map;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        dp = new int[N+1][N+1][3];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[1][2][0] = 1;
        for (int i = 3; i <= N; i++) if(map[1][i] == 0) dp[1][i][0] = dp[1][i-1][0];
        for (int i = 2; i <= N; i++) {
            for (int j = 3; j <= N; j++) {
                if(map[i][j] ==1 ) continue;
                // i-1, j-1
                if (map[i][j - 1] == 0 && map[i - 1][j] == 0) {
                    dp[i][j][2] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
                }
                // i-1, j
                if (map[i - 1][j] == 0) {
                    dp[i][j][1] = dp[i - 1][j][1] + dp[i - 1][j][2];
                }
                // i, j-1
                if (map[i][j - 1] == 0) {
                    dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][2];
                }
            }
        }

//        for (int i = 1; i <= N; i++) {
//            for (int j = 1; j <= N; j++) {
//                System.out.print("( " + dp[i][j][0] + " " + dp[i][j][1] + " " +dp[i][j][2]+" ) " );
//            }
//            System.out.println();
//        }
        System.out.println(dp[N][N][0] + dp[N][N][1] + dp[N][N][2]);
        br.close();
    }
}