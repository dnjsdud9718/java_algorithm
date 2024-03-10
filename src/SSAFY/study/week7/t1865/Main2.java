package SSAFY.study.week7.t1865;

// 웜홀 가짜 정점 도입(제대로 이해하고 풀기)
// 최단거리를 구하는 문제가 아니라, 음의 사이클을 판별하는 문제.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {
    static int T, N, M, W;
    static Edge[] edges;
    static int eLen;
    static int[] dist;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            dist = new int[N + 1]; // initialized zero
            edges = new Edge[M * 2 + W + 1];
            eLen = 0;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                edges[eLen++] = new Edge(u, v, w);
                edges[eLen++] = new Edge(v, u, w);
            }
            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                edges[eLen++] = new Edge(u, v, -1 * w);
            }
            boolean cycle = false;
            for (int i = 0; i <= N; i++) {
                for (int j = 0; j < eLen; j++) {
                    Edge e = edges[j];
                    if (dist[e.u] + e.w < dist[e.v]) {
                        if(i==N){
                            cycle = true;
                            break;
                        }
                        dist[e.v] = dist[e.u] + e.w;
                    }
                }
                if(cycle) break;
            }
            if(cycle) sb.append("YES");
            else sb.append("NO");
            sb.append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    static class Edge {
        int u, v, w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
}

