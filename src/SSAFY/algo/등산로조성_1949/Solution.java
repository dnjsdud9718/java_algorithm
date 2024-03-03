package SSAFY.algo.등산로조성_1949;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {
    static int T, N, K, pico, pLen, answer;
    static int[][] map;
    static int[] pr;
    static int[] pc;
    static Deque<int[]> queue;
    static boolean[][] visited;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            pico = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    pico = Math.max(pico, map[i][j]);
                }
            }
            pr = new int[5];
            pc = new int[5];
            pLen = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(map[i][j] != pico) continue;
                    pr[pLen] = i;
                    pc[pLen++] = j;
                }
            }
//            System.out.println(Arrays.toString(pr));
//            System.out.println(Arrays.toString(pc));

            answer = Integer.MIN_VALUE;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int k = 1; k <= K; k++) {
                        map[i][j] -= k;
                        for (int p = 0; p < pLen; p++) {
                            bfs(pr[p], pc[p]);
                        }
                        map[i][j] += k;
                    }
                }
            }
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    static void bfs(int r, int c) {
        queue = new ArrayDeque<>();
//        visited = new boolean[N][N];
//        visited[r][c] = true;
        queue.add(new int[]{r, c, 1});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            answer = Math.max(answer, cur[2]);
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(nr < 0 || nr == N || nc < 0 || nc == N || /*visited[nr][nc] ||*/ map[cur[0]][cur[1]] <= map[nr][nc]) continue;
//                visited[nr][nc] = true;
                queue.add(new int[]{nr, nc, cur[2] + 1});
            }
        }
    }
}
