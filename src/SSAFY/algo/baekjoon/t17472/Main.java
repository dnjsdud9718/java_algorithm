package SSAFY.algo.baekjoon.t17472;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, answer;
    static int[][] map;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static Deque<int[]> queue;
    static int nodes;
    static PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> {
        return o1.w - o2.w;
    });
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int v = Integer.parseInt(st.nextToken());
                if(v==1) map[i][j] = -1;
                else map[i][j] = 0;
            }
        }
//      섬 넘버링
        nodes = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == -1) {
                    map[i][j] = nodes;
                    bfs(i, j, nodes++);
                }
            }
        }
//        for (int i = 0; i < N; i++) {
//            System.out.println(Arrays.toString(map[i]));
//        }
//      모든 다리 건설
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 0) continue;
                makeBridge(i, j, map[i][j]);
            }
        }
//      MST
        nodes = nodes - 1;
        parents = new int[nodes+1];
        for (int i = 1; i <= nodes; i++) parents[i] = i;
        int cnt=0;
        answer = 0;
        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            int u = e.u;
            int v = e.v;
            int w = e.w;
            if (union(u,v)) {
//                System.out.println(u + " " + v +" "+w);
                answer += w;
                cnt++;
            }
            if(cnt == nodes-1) break;
        }
        if(cnt == nodes-1) System.out.println(answer);
        else System.out.println(-1);

        br.close();
    }

    static int find(int x) {
        if(parents[x] == x) return x;
        return (parents[x] = find(parents[x]));
    }

    static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return false;
        if (x < y) parents[y] = x;
        else parents[x] = y;
        return true;
    }
    static void makeBridge(int r, int c, int n) {
        for (int d = 0; d < 4; d++) {
            int cnt = 0;
            int nr = r;
            int nc = c;
            while (true) {
                nr = nr + dr[d];
                nc = nc + dc[d];
                if (nr < 0 || nr == N || nc < 0 || nc == M || map[nr][nc] == n) break;
                if (map[nr][nc] != 0) {
                    if( cnt >= 2) pq.add(new Edge(n, map[nr][nc], cnt));
                    break;
                }
                cnt++;
            }
        }
    }
    static void bfs(int r, int c, int n) {
        queue = new ArrayDeque<>();
        queue.add(new int[]{r, c});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if (nr < 0 || nr == N || nc < 0 || nc == M || map[nr][nc] != -1) continue;
                map[nr][nc] = n;
                queue.add(new int[]{nr, nc});
            }
        }
    }

    static class Edge {
        int u,v, w;
        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
}
