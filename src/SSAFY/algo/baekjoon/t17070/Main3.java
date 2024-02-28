package SSAFY.algo.baekjoon.t17070;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    dp
 */
public class Main3 {
    static int N;
    static int[][] map;
    static int[] dr = {0, 1, 1};
    static int[] dc = {1, 1, 0};
    static int[][] dirTbl = {
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

    public static int dfs(int r, int c, int dir) {
        if (r == N - 1 && c == N - 1) {
            return 1;
        }
        int answer = 0;
        for (int i = 0; i < 3; i++) {
            if(dirTbl[dir][i] == -1) continue;
            int nr = r + dr[dirTbl[dir][i]];
            int nc = c + dc[dirTbl[dir][i]];
            if (canGo(nr, nc, dirTbl[dir][i])) {
                answer += dfs(nr, nc, dirTbl[dir][i]);
            }
        }

        return answer;
    }

    public static boolean canGo(int r, int c, int dir) {
        switch (dir) {
            case 0: // 가로
                if(c == N  || map[r][c] == 1) return false;
                break;
            case 1: // 대각
                if(r == N || c == N || map[r][c] == 1 || map[r-1][c] == 1 || map[r][c-1] == 1) return false;
                break;
            case 2: // 세로
                if(r == N || map[r][c] == 1) return false;
                break;
        }
        if(map[r][c] == 1) return false;
        return true;
    }
}
