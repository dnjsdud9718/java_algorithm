package SSAFY.study.week4.t11725;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
    baekjoon 11725 트리의 부모 찾기
    루트 없는 트리가 주어진다. 이때, 트리의 루트를 1이라고 정했을 때, 각 노드의 부모를 구하는 프로그램을 작성
    7
    1 6                  1
    6 3                 / \
    3 5                6   4
    4 1               /   / \
    2 4              3   7   2
    4 7             /
                   5
    양방향 그래프를 인접 리스트로 만든다.
    1번에서 BFS 수행하며 인접한 노드의 부모를 현재 노드로 설정.
    부모를 저장하는 배열 parents에 부모를 저장.
 */
public class Main {
    static int N;
    static int[] parents;
    static List<Integer>[] list;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        list = new List[N + 1];
        parents = new int[N + 1];
        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) list[i] = new ArrayList<>();

        // tree -> N개 노드 N-1간선
        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list[u].add(v);
            list[v].add(u);
        }
        // BFS
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        visited[1] = true;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : list[cur]) {
                if(visited[next]) continue;
                visited[next] = true;
                parents[next] = cur;
                queue.add(next);
            }
        }

        for (int i = 2; i <= N; i++) sb.append(parents[i]).append('\n');
        System.out.println(sb.toString());
        br.close();
    }
}
