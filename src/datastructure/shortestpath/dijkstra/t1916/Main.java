package datastructure.shortestpath.dijkstra.t1916;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    다익스트라 1916 최소 비용 구하기
    5
    8
    1 2 2
    1 3 3
    1 4 1
    1 5 10
    2 4 2
    3 4 1
    3 5 1
    4 5 3
    1 5
 */
public class Main {
    static int N, M, start, end;
    static List<Edge>[] list;
    static int[] dp;
    static PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> {
        return e1.w - e2.w;
    });
    static final int INF = 987_654_321;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        list = new List[N + 1];
        visited = new boolean[N + 1];
        dp = new int[N + 1]; // dp[i] : start에서 i까지 가는 최단 거리 저장
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
            dp[i] = INF;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[u].add(new Edge(v, w));
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        dp[start] = 0; // start 에서  start로 가는 최단 거리.
        pq.add(new Edge(start, 0)); // 다음 노드를 선택할 때, 항상 아직 방문하지 않은 노드 중  출발점에서 가는 경로가 가장 작은 값을 택해야 한다. 그래서 w : 최단거리가 필요.
        while (!pq.isEmpty()) {
            Edge cur = pq.poll(); // 다음 노드를 선택하는 곳.
            if(visited[cur.v]) continue;
            visited[cur.v] = true;
            for (Edge next : list[cur.v]) { // 시작점에서 현재 노드를 거쳐 다른 노드로 가는 경로 더 작다면 경로를 갱신해줘야 한다.
                if(dp[next.v] <= dp[cur.v] + next.w) continue;
                dp[next.v] = dp[cur.v] + next.w;
                pq.add(new Edge(next.v, dp[next.v]));
            }
        }
        System.out.println(dp[end]);
        br.close();
    }
    static class Edge{
        int v;
        int w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}
