package SSAFY.algo.최적경로1247;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution5 {
    static final int INF = 987_654_321;
    static int N;
    static Point[] points;
    static int[][] dist;
    static int[][] dp;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            points = new Point[N + 2];
            dist = new int[N + 2][N + 2];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N + 2; i++) {
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                points[i] = new Point(r, c);
            }
            for (int i = 0; i < N + 1; i++) {
                for (int j = i+1; j < N + 2; j++) {
                    int d = Math.abs(points[i].r - points[j].r) + Math.abs(points[i].c - points[j].c);
                    dist[i][j] = dist[j][i] = d;
                }
//                System.out.println(Arrays.toString(dist[i]));
            }
            dp = new int[N + 2][(1 << (N + 2))];
            for (int i = 0; i < N + 2; i++) Arrays.fill(dp[i], -1);

            int answer = dfs(0, 3); // 회사와 집을 방문했다고 가정.
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    static int dfs(int here, int check) {
        if (check == (1 << (N + 2)) - 1) {
            return dist[here][1]; // 현재 위치에서 집으로 가는 거리.
        }
        if(dp[here][check] != -1) return dp[here][check];
        dp[here][check] = INF;
        for (int i = 2; i < N + 2; i++) {
            if((check & (1<<i))!=0) continue;
            int tmp = dfs(i, check | (1 << i));
            dp[here][check] = Math.min(dp[here][check], tmp + dist[here][i]);
        }
        return dp[here][check];
    }
    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
