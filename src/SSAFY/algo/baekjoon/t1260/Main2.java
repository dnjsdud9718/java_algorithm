package SSAFY.algo.baekjoon.t1260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main2 {
    static int N, M, S;
    static int[][] matrix;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        matrix = new int[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            // 양방향
            matrix[u][v] = matrix[v][u] = 1;
        }

        dfs(S);
//        sb.setCharAt(sb.length() - 1, '\n');
        sb.append("\n");
        for (int i = 0; i <= N; i++) matrix[0][i] = 0;

        bfs(S);
//        sb.setLength(sb.length());
        System.out.println(sb.toString());
        br.close();
    }

    public static void dfs(int u) {
        matrix[0][u] = 1;
        sb.append(u).append(" ");
        for (int v = 1; v <= N; v++) {
            if(matrix[0][v] == 1 || matrix[u][v] == 0) continue;
            dfs(v);
        }
    }

    public static void bfs(int u) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(u);
        matrix[0][u] = 1;
        while (!queue.isEmpty()) {
            int x = queue.poll();
            sb.append(x).append(" ");
            for (int v=1; v<=N; v++) {
                if(matrix[0][v] == 1 || matrix[x][v] == 0) continue;
                matrix[0][v] = 1;
                queue.add(v);
            }
        }
    }
}
