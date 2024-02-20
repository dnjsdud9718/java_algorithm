package SSAFY.algo.baekjoon.t2252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
    baeckjoon 2252 줄 세우기
    위상정렬
 */
public class Main {
    static int N, M;
    static List<Integer>[] list;
    static int[] inDegrees;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        inDegrees = new int[N + 1];
        list = new List[N + 1];
        for (int i = 1; i <= N; i++) list[i] = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list[u].add(v);
            inDegrees[v]++;
        }
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if(inDegrees[i] == 0) {
                queue.add(i);
                sb.append(i).append(' ');
            }
        }
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : list[cur]) {
                if(inDegrees[next] == 0) continue;
                inDegrees[next]--;
                if(inDegrees[next] == 0) {
                    queue.add(next);
                    sb.append(next).append(' ');
                }
            }
        }
        System.out.println(sb);
        br.close();
    }
}
