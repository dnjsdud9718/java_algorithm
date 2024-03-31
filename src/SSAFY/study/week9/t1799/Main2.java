package SSAFY.study.week9.t1799;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {
    static int N, answer1, answer2;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, -1};
    static int[] dc = {-1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        backtracking1(0, 0, 0);
        backtracking2(0, 1, 0);
        System.out.println(answer1+answer2);
        br.close();
    }
    static void backtracking1(int row, int col, int cnt){
        if (cnt >= answer1) {
            answer1 = cnt;
        }
        for (int i = row; i < N; i++) {
            for (int j = i % 2; j < N; j += 2) {
                if(i==row && j < col) continue;
                if(map[i][j] <= 0 || visited[i][j] || !check(i,j)) continue;
                visited[i][j] = true;
                backtracking1(i, j, cnt + 1);
                visited[i][j] = false;
            }
        }
    }
    static void backtracking2(int row,int col, int cnt){
        if (cnt > answer2) {
            answer2= cnt;
        }
        for (int i = row; i < N; i++) {
            for (int j = (i+1) % 2; j < N; j += 2) {
                if(i==row && j < col) continue;
                if(map[i][j] <= 0 || visited[i][j] || !check(i,j)) continue;
                visited[i][j] = true;
                backtracking2(i, j, cnt + 1);
                visited[i][j] = false;
            }
        }
    }

    static boolean check(int row, int col) {
        int nr, nc;
        for (int d = 0; d < 2; d++) {
            nr = row;
            nc = col;
            while (true) {
                nr = nr + dr[d];
                nc = nc + dc[d];
                if(nr < 0 || nc < 0 || nr == N || nc==N) break;
                if(visited[nr][nc]) return false;
            }
        }
        return true;
    }
}



