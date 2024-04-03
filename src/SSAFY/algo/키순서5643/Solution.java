package SSAFY.algo.키순서5643;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int T;
    static int N, M;
    static List<Integer>[] list;
    static List<Integer>[] revList;
    static int answer;
    static boolean[] visited;
    static int[] counts;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());
            list = new List[N + 1];
            revList = new List[N + 1];
            counts = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                list[i] = new ArrayList<>();
                revList[i] = new ArrayList<>();
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                list[a].add(b);
                revList[b].add(a);
            }
            visited = new boolean[N + 1];
            for (int i = 1; i <= N; i++) {
                Arrays.fill(visited, false);
                bfs(list, i);
            }
            for (int i = 1; i <= N; i++) {
                Arrays.fill(visited, false);
                bfs(revList, i);
            }
            answer = 0;
            System.out.println(Arrays.toString(counts));
            for (int i = 1; i <= N; i++) {
                if(counts[i] == N-1) answer++;
            }
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    static void bfs(List<Integer>[] list, int x) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(x);
        visited[x] = true;
        int cnt = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : list[cur]) {
                if (!visited[next]) {
                    visited[next] = true;
                    cnt++;
                    queue.add(next);
                }
            }
        }
        counts[x] += cnt;
    }
}
