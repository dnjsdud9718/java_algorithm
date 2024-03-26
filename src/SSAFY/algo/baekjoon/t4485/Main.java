package SSAFY.algo.baekjoon.t4485;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int INF = 987_654_321;
    static int N;
    static int[][] map;
    static int[][] cost;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int cnt = 1;
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            map = new int[N][N];
            cost = new int[N][N];

            for (int i = 0; i < N; i++) {
                Arrays.fill(cost[i], INF);
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)->{
                return o1[2] - o2[2];
            });
            pq.add(new int[]{0, 0, cost[0][0]});
            cost[0][0] = map[0][0];
            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                if(cur[0] == N-1 && cur[1] == N-1) break;
                for (int d = 0; d < 4; d++) {
                    int nr = cur[0] + dr[d];
                    int nc = cur[1] + dc[d];
                    if(nr < 0 || nr == N || nc < 0 || nc == N) continue;
                    if (cost[nr][nc] > cost[cur[0]][cur[1]] + map[nr][nc]) {
                        cost[nr][nc] = cost[cur[0]][cur[1]] + map[nr][nc];
                        pq.add(new int[]{nr, nc, cost[nr][nc]});
                    }
                }
            }
            sb.append("Problem ").append(cnt++).append(": ").append(cost[N-1][N-1]).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }
}
