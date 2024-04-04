package SSAFY.algo.보급로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    static final int INF = 987_654_321;
    static int T, N, min;
    static int[][] map;
    static int[][] dist;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
        return o1[2] - o2[2];
    });
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            dist = new int[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(dist[i], INF);
                String s = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = s.charAt(j) - '0';
                }
            }
            dist[0][0] = 0;
            pq.clear();
            pq.add(new int[]{0, 0, 0});
            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                if (cur[0] == N - 1 && cur[1] == N - 1) {
                    min = cur[2];
                    break;
                }
                for (int d = 0; d < 4; d++) {
                    int nr = cur[0] + dr[d];
                    int nc = cur[1] + dc[d];
                    if(nr < 0 || nr == N || nc < 0 || nc == N) continue;
                    if (dist[nr][nc] > cur[2] + map[nr][nc]) {
                        dist[nr][nc] = cur[2] + map[nr][nc];
                        pq.add(new int[]{nr, nc, dist[nr][nc]});
                    }
                }
            }
            sb.append("#").append(t).append(" ").append(min).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}
