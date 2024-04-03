package datastructure.shortestpath.bellman.t1738;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 벨만-포드(Edge List) + BFS

*/
public class Main {
    static final int INF = -987654321;
    static int N, M;
    static Edge[] edges;
    static int[] dist;
    static int[] path;
    static Deque<Integer> queue;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new Edge[M];
        dist = new int[N + 1];
        path = new int[N + 1];
        Arrays.fill(dist, INF);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(u, v, w);
        }

        queue = new ArrayDeque<>();
        visited = new boolean[N + 1];
        dist[1] = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                Edge e = edges[j];
                if (dist[e.u] > INF && dist[e.v] < dist[e.u] + e.w) {
                    if (i == N) { // cycle
                        queue.add(e.v);
                        visited[e.v] = true;
                    }
                    path[e.v] = e.u;
                    dist[e.v] = dist[e.u] + e.w;
                }
            }
        }
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if(cur == N) break;
            for (Edge e : edges) {
                if (e.u == cur && !visited[e.v]) {
                    visited[e.v] = true;
                    queue.add(e.v);
                }
            }
        }
        if (visited[N]) {
            System.out.println(-1);
        } else {
            int p = N;
            queue.clear();
            while (true) {
                if(p == 0) break;
                queue.push(p);
                p = path[p];
            }
            while (!queue.isEmpty()) {
                System.out.printf("%d ", queue.pop());
            }
        }
        br.close();
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
