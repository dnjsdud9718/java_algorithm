package SSAFY.algo.하나로_1251;
/*
    MST 문제
    간선을 피타고라스로 계산하면 된다...

 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static double E;
    static double[] x;
    static double[] y;
    static int[] parents;
    static double ans;
    static PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> {
        if(o1.w > o2.w) return 1;
        else if(o1.w < o2.w) return -1;
        return 0;
    });
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            x = new double[N];
            y = new double[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                x[i] = Double.parseDouble(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                y[i] = Double.parseDouble(st.nextToken());
            }
            parents = new int[N];
            for (int i = 0; i < N; i++) {
                parents[i] = i;
            }
            E = Double.parseDouble(br.readLine());
            // 거리 계산
            pq.clear();
            for (int i = 0; i < N-1; i++) {
                for (int j = i+1; j < N; j++) {
                    double d = calDistance(x[i], y[i], x[j], y[j]);
                    pq.add(new Edge(i, j, E*d*d));
                }
            }
            ans = 0;
            int cnt = 0;
            while (!pq.isEmpty()) {
                if (cnt == N - 1) break;
                Edge e = pq.poll();
                int u = e.u;
                int v = e.v;
                double w = e.w;
                if (union(u, v)) {
                    ans += w;
                    cnt++;
                }
            }

            sb.append("#").append(t).append(' ').append(Math.round(ans)).append('\n');
        }
        System.out.println(sb);
        br.close();
    }
    static int find(int x) {
        if(parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }

    static boolean union(int i, int j) {
        i = find(i);
        j = find(j);
        if(i == j) return false;
        if (i > j) {
            parents[i] = j;
        } else parents[j] = i;
        return true;
    }
    static class Edge {
        int u,v;
        double w;
        public Edge(int u, int v, double w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "u=" + u +
                    ", v=" + v +
                    ", w=" + w +
                    '}';
        }
    }
    public static double calDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}
