package SSAFY.algo.baekjoon.t1600;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {1, 0, -1, 0, -2, -2, -1, -1, 1, 1, 2, 2};
    static int[] dc = {0, 1, 0, -1, -1, 1, -2, 2, -2, 2, -1, 1};
    static int K, R, C;
    static int[][] map;
    static boolean[][][] visited;
    static Deque<int[]> queue = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        visited = new boolean[R][C][K+1];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        queue.add(new int[]{0, 0, 0, 0}); // r,  c, k, cnt
        int answer = -1;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == R - 1 && cur[1] == C - 1) {
                answer = cur[3];
                break;
            }
            if(visited[cur[0]][cur[1]][cur[2]]) continue;
            visited[cur[0]][cur[1]][cur[2]] = true;
            for (int d = 0; d < 12; d++) {
                if(d > 3 && cur[2] == K) break;
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc] == 1) continue;
                queue.add(new int[]{nr, nc, (d > 3) ? cur[2]+1 : cur[2], cur[3] + 1});
            }
        }
        System.out.println(answer);

        br.close();
    }
}
