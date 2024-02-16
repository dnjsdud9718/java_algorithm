package SSAFY.algo.baekjoon.t1987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C, ans;
    static int[][] src;
    static int basic = 'A';
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        src = new int[R][C];
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                src[i][j] = s.charAt(j) - basic;
            }
        }

        ans = 1;
        dfs(0, 0, (1<<src[0][0]), 1);
        System.out.println(ans);
        br.close();
    }

    public static void dfs(int y, int x, int check, int cnt) { // permutation code랑 유사하다.
        ans = Math.max(ans, cnt);
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(ny < 0 || ny == R || nx < 0 || nx == C) continue;
            if((check & (1<<src[ny][nx])) != 0) continue;
            dfs(ny, nx, check | (1<<src[ny][nx]), cnt + 1);
        }
    }

}

