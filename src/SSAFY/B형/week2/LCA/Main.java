package SSAFY.B형.week2.LCA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static Node[] nodes;
    static int[] parents;
    static int[][] pairs;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        nodes = new Node[N + 1];
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) nodes[i] = new Node(i);
        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            nodes[u].add(v);
            nodes[v].add(u);
        }
        M = Integer.parseInt(br.readLine());
        pairs = new int[M][2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            pairs[i][0] = n1;
            pairs[i][1] = n2;
        }
        bfs(1);
        for (int m = 0; m < M; m++) {
            List<Integer> a1 = ancestors(pairs[m][0]);
            List<Integer> a2 = ancestors(pairs[m][1]);
            int lca = 1;
            int i = a1.size()-1;
            int j = a2.size()-1;
            while (i > -1 && j > -1 && a1.get(i).equals(a2.get(j))) {
                lca = a1.get(i);
                i--; j--;
            }
            sb.append(lca).append('\n');
        }
        System.out.println(sb);
        br.close();
    }

    public static List<Integer> ancestors(int n) {
        List<Integer> a = new ArrayList<>();
        a.add(n);
        while (parents[n] != 0) {
            a.add(parents[n]);
            n = parents[n];
        }
        return a;
    }
    public static void bfs(int n) {
        Deque<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        queue.add(1);
        visited[1] = true;
        while (!queue.isEmpty()) {
            int cur = queue.peek();
            queue.poll();
            for (int next : nodes[cur].next) {
                if(visited[next]) continue;
                visited[next] = true;
                parents[next] = cur;
                queue.add(next);
            }
        }

    }
    static class Node {
        int n;
        List<Integer> next;

        public Node(int n) {
            this.n = n;
            this.next = new ArrayList<>();
        }

        public void add(int c) {
            next.add(c);
        }
    }
}
