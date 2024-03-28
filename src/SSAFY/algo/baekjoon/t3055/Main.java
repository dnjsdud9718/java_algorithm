package SSAFY.algo.baekjoon.t3055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 987_654_321;
    static int N, M;
    static int[][] map;
    static int sr, sc, er, ec;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static Deque<int[]> queue = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = s.charAt(j);
                switch (c) {
                    case '*':
                        map[i][j] = INF;
                        queue.add(new int[]{i, j, 0});
                        break;
                    case 'X':
                        map[i][j] = INF;
                        break;
                    case 'D':
                        er = i;
                        ec = j;
                        map[i][j] = INF;
                        break;
                    case 'S':
                        sr = i;
                        sc = j;
                        break;
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(nr < 0 || nr == N || nc < 0 || nc == M || map[nr][nc] > 0) continue;
                map[nr][nc] = cur[2] + 1;
                queue.add(new int[]{nr, nc, map[nr][nc]});
            }
        }

        map[er][ec] = -1;
        boolean flag = false;
        int answer = 0;
        queue.add(new int[]{sr, sc, 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(nr < 0 || nr == N || nc < 0 || nc == M || map[nr][nc] == INF) continue;
                if (map[nr][nc] == -1) {
                    flag = true;
                    answer = cur[2] + 1;
                    break;
                }
                if (map[nr][nc] == 0) {
                    map[nr][nc] = cur[2] + 1;
                    queue.add(new int[]{nr, nc, cur[2] + 1});
                }
                if (map[nr][nc] > cur[2] + 1) {
                    map[nr][nc] = cur[2] + 1;
                    queue.add(new int[]{nr, nc, cur[2] + 1});
                }
            }
            if(flag) break;
        }

//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                if(map[i][j] == INF) System.out.print("INF ");
//                else System.out.printf("%d ", map[i][j]);
//            }
//            System.out.println();
//        }
        System.out.println(!flag ? "KAKTUS" : answer);
        br.close();
    }
}