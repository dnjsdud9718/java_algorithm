package SSAFY.algo.baekjoon.t17070;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main4 {
    static int N;
    static int[][] map;
    static int[] dr = {0, 1, 1};
    static int[] dc = {1, 1, 0};
    static int[][] tbl = {
            {0, 1, -1},
            {0, 1, 2},
            {1, 2, -1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(dfs(0, 1, 0));
        br.close();
    }

    static int dfs(int r, int c, int dir) {
        if (r == N - 1 && c == N - 1) {
            return 1;
        }
        int answer = 0;
        for (int d = 0; d < 3; d++) {
            if(tbl[dir][d] == -1) continue;
            int nr = r + dr[tbl[dir][d]];
            int nc = c + dc[tbl[dir][d]];
            if (canGo(nr, nc, tbl[dir][d])) {
                answer += dfs(nr, nc, tbl[dir][d]);
            }
        }
        return answer;
    }

    static boolean canGo(int r, int c, int dir) {
        if(r == N || c==N) return false;
        switch (dir) {
            case 0 -> {
                return map[r][c] != 1;
            }
            case 1 -> {
                return map[r][c] != 1 && map[r - 1][c] != 1 && map[r][c - 1] != 1;
            }
            case 2 -> {
                return map[r][c] != 1;
            }
        }
        return false;
    }
}
