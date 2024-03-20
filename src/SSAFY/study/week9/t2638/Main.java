package SSAFY.study.week9.t2638;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.ArrayDeque;

public class Main {
    static final int INF = 987_654_321;
    static int R, C, answer;
    static int[][] map;
    static boolean[][] visited;
    static Deque<int[]> queue = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        answer = 0;
        while (!check()) {
            bfs();
            answer++;

        }
        System.out.println(answer);
        br.close();
    }

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static void bfs() {
        queue.clear();
        queue.add(new int[]{0, 0});
        map[0][0] = INF;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(nr < 0 || nr == R || nc < 0 || nc == C || map[nr][nc] == INF) continue;
                if (map[nr][nc] == 0) {
                    map[nr][nc] = INF;
                    queue.add(new int[]{nr, nc});
                }else{
                    map[nr][nc]++;
                }
            }
        }
        melt();
//        print();
    }

    static void print() {
        for (int i = 0; i < R; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println("-------");
    }
    static boolean check() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j] == 1) return false;
            }
        }
        return true;
    }
    static void melt(){
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j] != INF && map[i][j] > 2) map[i][j] = 0;
                else if(map[i][j] == 2) map[i][j] = 1;
                if(map[i][j] == INF) map[i][j] = 0;
            }
        }
    }
}
