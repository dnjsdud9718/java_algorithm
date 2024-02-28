package SSAFY.algo.baekjoon.t17070;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/*
    3
    0 0 0
    0 0 0
    0 0 0
 */
public class Main {
    static int N, COUNT;
    static int[] dr = {0, 1, 1};
    static int[] dc = {1, 1, 0};
    static int[][] tbl = {
            {0, 1, -1},
            {0, 1, 2},
            {1, 2, -1}
    };
    static int[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited =new boolean[N+1][N+1];
        for(int i=0; i<=N; i++) visited[i][N] = true;
        Arrays.fill(visited[N], true);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) visited[i][j] = true;
            }
        }
        COUNT = 0;
        // (0,1) 가로에서 시작
        dfs(0, 1, 0);
        System.out.println(COUNT);
        br.close();
    }

    public static void dfs(int r, int c, int dir) {
        if (r == N - 1 && c == N - 1) {
            COUNT++;
            return;
        }
        for (int i = 0; i < 3; i++) {
            if (tbl[dir][i] != -1 && canGo(r, c, tbl[dir][i])) {
                int nr = r + dr[tbl[dir][i]];
                int nc = c + dc[tbl[dir][i]];
                if(nr == N || nc == N || map[nr][nc] == 1 || visited[nr][nc]) continue;
                visited[nr][nc] = true;
                dfs(nr, nc, tbl[dir][i]);
                visited[nr][nc] = false;
            }
        }
    }
    public static boolean canGo(int r, int c, int dir) {
        switch (dir) {
            case 0:
                return !visited[r][c + 1];
            case 1:
                return !visited[r][c + 1] && !visited[r + 1][c] && !visited[r + 1][c + 1];
            case 2:
                return !visited[r + 1][c];
            default:
                return false;
        }
    }
}
