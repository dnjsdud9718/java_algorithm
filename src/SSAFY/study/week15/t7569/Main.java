package SSAFY.study.week15.t7569;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int N, M, H;
    static int[][][] map;
    static boolean[][][] visited;
    static int[] dr = {0, 1, 0, -1, 0, 0};
    static int[] dc = {1, 0, -1, 0, 0, 0};
    static int[] dh = {0, 0, 0, 0, -1, 1};
    static Deque<int[]> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[N][M][H];
        visited = new boolean[N][M][H];

        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                    if (map[i][j][k] == 1) {
                        visited[i][j][k] = true;
                        queue.add(new int[]{i, j, k, 0});
                    }
                }
            }
        }
        int max = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            max = Math.max(max, cur[3]);
            for (int d = 0; d < 6; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                int nh = cur[2] + dh[d];

                if (nr < 0 || nr == N || nc < 0 || nc == M || nh < 0 || nh == H
                    || visited[nr][nc][nh] || map[nr][nc][nh] == -1) {
                    continue;
                }
                visited[nr][nc][nh] = true;
                map[nr][nc][nh] = 1;
                queue.add(new int[]{nr, nc, nh, cur[3] + 1});
            }
        }
        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j][k] == 0) {
                        max = -1;
                        break;
                    }
                }
                if (max < 0) {
                    break;
                }
            }
            if (max < 0) {
                break;
            }
        }

        System.out.println(max);
        br.close();
    }
}

