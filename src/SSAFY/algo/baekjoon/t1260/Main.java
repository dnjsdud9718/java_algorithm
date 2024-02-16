package SSAFY.algo.baekjoon.t1260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
    4 5 1
    1 2
    1 3
    1 4
    2 4
    3 4
    DFS BFS
 */
public class Main {
    static int N, M, S;
    static List<Integer>[] list;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        list = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            // 양방향
            list[u].add(v);
            list[v].add(u);
        }
        for (int i = 1; i <= N; i++) {
            Collections.sort(list[i]);
        }
//        for(int i=1; i<=N; i++) System.out.println(Arrays.toString(list[i].toArray()));
        visited = new boolean[N + 1];
        dfs(S);
        sb.append("\n");
        visited = new boolean[N + 1];
        bfs(S);
        System.out.println(sb.toString());
        br.close();
    }

    public static void dfs(int u) {
        visited[u] = true;
        sb.append(u).append(" ");
        for (int v : list[u]) {
            if(visited[v]) continue;
            dfs(v);
        }
    }

    public static void bfs(int u) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(u);
        visited[u] = true;
        while (!queue.isEmpty()) {
            int x = queue.poll();
            sb.append(x).append(" ");
            for (int v : list[x]) {
                if(visited[v]) continue;
                visited[v] = true;
                queue.add(v);
            }
        }
    }
}
