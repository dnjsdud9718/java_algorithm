package SSAFY.algo.baekjoon.t14502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;
import java.util.concurrent.DelayQueue;

public class Main {
    static int N, M, max;
    static int[][] map;
    static boolean[][] visited;
    static int[] tgt = new int[3];
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static Ground[] gd;
    static int gdSize;
    static Deque<int[]> queue = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        gd = new Ground[N * M];
        visited = new boolean[N][M];
        gdSize = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    gd[gdSize++] = new Ground(i, j);
                }
            }
        }
        max = Integer.MIN_VALUE;
        // 벽을 안 세웠을 때도 확인해 봐야지
        bfs();
        comb(0, 0);
        System.out.println(max);
        br.close();
    }
    public static void bfs() {
        // 전염 시작
        visited = new boolean[N][M];
        queue.clear();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 2 && !visited[i][j]) {
                    visited[i][j] = true;
                    queue.add(new int[]{i, j});
                    while (!queue.isEmpty()) {
                        int[] cur = queue.poll();
                        for (int d = 0; d < 4; d++) {
                            int nr = cur[0] + dr[d];
                            int nc = cur[1] + dc[d];
                            if(nr<0 || nr == N || nc <0 || nc == M || visited[nr][nc] || map[nr][nc]==1) continue;
                            visited[nr][nc] = true;
                            if(map[nr][nc] == 0) map[nr][nc] = 3;
                            queue.add(new int[]{nr, nc});
                        }
                    }

                }
            }
        }
        // 전염 안된 곳 계산 및 전염 전으로 되돌리기
        int zeros = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 0) zeros++;
                if(map[i][j] == 3) map[i][j] = 0;
            }
        }
        // 계산
        max = Math.max(max, zeros);
    }
    public static void comb(int srcIdx, int tgtIdx) {
        if (tgtIdx == 3) {
            // 벽 만들기
            for (int i = 0; i < tgt.length; i++) {
                map[gd[tgt[i]].r][gd[tgt[i]].c] = 1;
            }
            bfs();
//            // 전염 시작
//            visited = new boolean[N][M];
//            queue.clear();
//            for (int i = 0; i < N; i++) {
//                for (int j = 0; j < M; j++) {
//                    if (map[i][j] == 2 && !visited[i][j]) {
//                        visited[i][j] = true;
//                        queue.add(new int[]{i, j});
//                        while (!queue.isEmpty()) {
//                            int[] cur = queue.poll();
//                            for (int d = 0; d < 4; d++) {
//                                int nr = cur[0] + dr[d];
//                                int nc = cur[1] + dc[d];
//                                if(nr<0 || nr == N || nc <0 || nc == M || visited[nr][nc] || map[nr][nc]==1) continue;
//                                visited[nr][nc] = true;
//                                if(map[nr][nc] == 0) map[nr][nc] = 3;
//                                queue.add(new int[]{nr, nc});
//                            }
//                        }
//
//                    }
//                }
//            }
//            // 전염 안된 곳 계산 및 전염 전으로 되돌리기
//            int zeros = 0;
//            for (int i = 0; i < N; i++) {
//                for (int j = 0; j < M; j++) {
//                    if(map[i][j] == 0) zeros++;
//                    if(map[i][j] == 3) map[i][j] = 0;
//                }
//            }
//            // 계산
//            max = Math.max(max, zeros);
            // 벽 없애기
            for (int i = 0; i < tgt.length; i++) {
                map[gd[tgt[i]].r][gd[tgt[i]].c] = 0;
            }
            return;
        }

        for (int i = srcIdx; i < gdSize; i++) {
            tgt[tgtIdx] = i;
            comb(i + 1, tgtIdx + 1);
        }
    }
    static class Ground {
        int r, c;
        public Ground(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
