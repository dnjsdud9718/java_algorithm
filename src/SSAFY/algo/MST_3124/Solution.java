package SSAFY.algo.MST_3124;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    static int V, E, CNT;
    static int[] parents;
    static long answer;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            parents = new int[V + 1];
            for (int i = 1; i <= V; i++) parents[i] = i;
            PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> {
                return o1.w - o2.w;
            });
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                pq.add(new Edge(u, v, w));
            }
            CNT = 0;
            answer = 0L;
            while (!pq.isEmpty()) {
                if(CNT == V-1) break;
                Edge cur = pq.poll();
                if (union(cur.u, cur.v)) {
                    System.out.println(cur);
                    CNT+=1;
                    answer += cur.w;
                }
            }

            sb.append('#').append(t).append(' ').append(answer).append('\n');
        }
        System.out.println(sb);
        br.close();
    }

    public static int find(int x) {
        if(x == parents[x]) return x;
        return parents[x] = find(parents[x]);
    }

    public static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if(x == y) return false;
        if (x > y) {
            parents[x] = y;
        } else {
            parents[y] = x;
        }
        return true;
    }

    static class Edge{
        int u;
        int v;
        int w;

        public Edge(int u, int v, int w) {
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
}
