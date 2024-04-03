package datastructure.shortestpath.dijkstra.t1504;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int INF = 987_654_321;
    static int N, M, V1, V2, answer;
    static List<Node>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[u].add(new Node(v, w));
            list[v].add(new Node(u, w));
        }
        st = new StringTokenizer(br.readLine());
        V1 = Integer.parseInt(st.nextToken());
        V2 = Integer.parseInt(st.nextToken());
        answer = 0;
        int sToV1 = diji(1, V1);
        int sToV2 = diji(1, V2);
        int v1ToV2 = diji(V1, V2);
        int v1ToN = diji(V1, N);
        int v2ToN = diji(V2, N);

        int a = -1;
        if(sToV1 < INF && v1ToV2 < INF && v2ToN < INF) a = sToV1 + v1ToV2 + v2ToN;
        int b = -1;
        if(sToV2 < INF && v1ToV2 < INF && v1ToN < INF) b = sToV2 + v1ToV2 + v1ToN;

        if(a == -1 && b == -1) System.out.println(-1);
        else System.out.println(Math.min(a, b));
        br.close();
    }

    static int diji(int start, int end) {
        int[] dist = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(dist, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.v - o2.v;
        });
        dist[start] = 0;
        pq.add(new Node(start, 0));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if(visited[cur.k]) continue;
            visited[cur.k] = true;
            for (Node next : list[cur.k]) {
                if (!visited[next.k] && dist[next.k] > dist[cur.k] + next.v) {
                    dist[next.k] = dist[cur.k] + next.v;
                    pq.add(new Node(next.k, dist[next.k]));
                }
            }
        }
        return dist[end];
    }
    static class Node {
        int k, v;

        public Node(int k, int v) {
            this.k = k;
            this.v = v;
        }
    }
}
