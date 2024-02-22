package datastructure.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Prim {
    static int T, V, E;
    static long sum;
    static List<Edge>[] adjList;
    static boolean[] visited;
    static PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            adjList = new List[V + 1];
            for (int i = 1; i <= V; i++) {
                adjList[i] = new ArrayList<>();
            }
            visited = new boolean[V + 1];
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                adjList[u].add(new Edge(v, w));
                adjList[v].add(new Edge(u, w));
            }
            // 풀이
            pq.clear();
            sum = 0;
            int cnt = 1;
            visited[1] = true;
            pq.addAll(adjList[1]); // 1번 정점에서 갈 수 있는 모든 정점, 간선을 담는다.
            while (!pq.isEmpty()) {
                Edge edge = pq.poll();
                if(visited[edge.v]) continue;
                visited[edge.v] = true;
                sum += edge.w;
                cnt++;
                if(cnt == V) break;
//                pq.addAll(adjList[edge.v]);
                for (Edge e : adjList[edge.v]) {
                    if(visited[e.v]) continue;
                    pq.add(e);
                }
            }

            sb.append('#').append(t).append(' ').append(sum).append('\n');

        }
        System.out.println(sb);
        br.close();
    }
    static class Edge{
        int v, w;
        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}
