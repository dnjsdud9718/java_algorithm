package SSAFY.study.week2.t1238;

/*
    baekjoon 1238 파티
    이 문제는 모든 정점에서 다익스트라를 수행하거나, 플로이드 와샬(모든 정점에서 모든 정점으로 가는 최단 경로 계산)을 사용해도 해결되나,
    역방향 그래프를 사용하면 두 번의 다익스트라를 통해 문제를 해결할 수 있다.
    -> 역방향 그래프에서  B에서 A로 가는 경로가 있다면, 순방향 그래프에서 A에서 B로 가는 경로가 있다(자명)
    -> 역방향 그래프에서 B에서 A로 가는 최단 경로를 구했다면, 그 값은 순방향 그래프에서 A에서 B로 가는 최단 경로이다(자명)

    순방향 그래프 다익스트라 1번 수행
    -> X에서 출발하여 모든 노드로 가는 최단 경로 계산 -> 문제에서 요구하는 돌아가는 시간을 계산 가능
    역방향 그래프 다익스트라 1번 수행
    -> X에서 출발하여 모든 노드로 가는 최단 경로 계산 -> 모든 정점에서 X로 오는 최단 경로 계산과 같은 결과를 도출
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static final int INF = 987_654_321;
    static int N, M, X, answer;
    static List<Edge>[] graph;
    static List<Edge>[] reverse;
    static int[] dp1;
    static int[] dp2;
    static boolean[] visited;
    static PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
        return o1[1] - o2[1];
    });
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        graph = new List[N + 1];
        reverse = new List[N + 1];
        dp1 = new int[N + 1];
        dp2 = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            reverse[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, w));
            reverse[v].add(new Edge(u, w));
        }
        // X 마을에서 집으로 돌아갈 때, X에 모든 마을까지의 최단 경로
        dijkstra(graph, dp1, X);
        // 각 마을에서 X마을로 갈때, 각 마을에서 X 마을까지의 최단 경로
        dijkstra(reverse, dp2, X);
        for (int i = 1; i <= N; i++) {
            if (answer < dp1[i] + dp2[i]) {
                answer = dp1[i] + dp2[i];
            }
        }
//        System.out.println(Arrays.toString(dp2));
//        System.out.println(Arrays.toString(dp1));
        System.out.println(answer);
        br.close();
    }

    static void dijkstra(List<Edge>[] graph, int[] dp, int start) {
        visited = new boolean[N + 1];
        Arrays.fill(dp, INF);
        pq.clear();
        dp[start] = 0;
        pq.add(new int[]{start, 0});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if(visited[cur[0]]) continue;
            visited[cur[0]] = true;
            for (Edge next : graph[cur[0]]) {
                if (dp[next.v] > dp[cur[0]] + next.w) {
                    dp[next.v] = dp[cur[0]] + next.w;
                    pq.add(new int[]{next.v, dp[next.v]});
                }
            }
        }
    }
    static class Edge{
        int v,w;
        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}
