package SSAFY.algo.Contact1238;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int N, S;
    static List<Integer>[] list;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int t = 1; t <= 10; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());
            list = new List[101];
            for (int i = 1; i <= 100; i++) {
                list[i] = new ArrayList<>();
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N / 2; i++) {
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                list[u].add(v);
            }
            visited = new boolean[101];
            Deque<int[]> queue = new ArrayDeque<>();
            queue.add(new int[]{S, 1});
            visited[S] = true;
            int[] ans = new int[]{S, 1};
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                if (ans[1] < cur[1]) {
                    ans[0] = cur[0];
                    ans[1] = cur[1];
                } else if (ans[1] == cur[1]) {
                    ans[0] = Math.max(ans[0], cur[0]);
                }
                for (int x : list[cur[0]]) {
                    if(visited[x]) continue;
                    visited[x] = true;
                    queue.add(new int[]{x, cur[1]+1});
                }
            }
            sb.append('#').append(t).append(' ').append(ans[0]).append('\n');
        }
        System.out.println(sb);
        br.close();
    }

}
