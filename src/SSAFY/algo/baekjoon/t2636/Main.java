package SSAFY.algo.baekjoon.t2636;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int ans1, ans2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int x;
        ans1 = 0;
        while ((x = isDone()) != 0) {
            ans2 = x;
            bfs();
            ans1++;
        }
        System.out.println(ans1);
        System.out.println(ans2);
        br.close();
    }
    public static void bfs() {
        visited = new boolean[R][C];
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0});
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(nr < 0 || nr == R || nc < 0 || nc == C || visited[nr][nc]) continue;
                visited[nr][nc] = true;
                if(map[nr][nc] == 0) queue.add(new int[]{nr, nc});
                map[nr][nc] = 0;
            }
        }
    }

    public static int isDone() {
        int cnt=0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j] == 1) cnt++;
            }
        }
        return cnt;
    }
}
