package SSAFY.algo.baekjoon.t17472;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Prim {
    static int R, C, N, answer;
    static int[][] map;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static Deque<int[]> queue;
    static List<Node>[] list;
    static boolean[] visited;
    static PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
        return o1.w - o2.w;
    });
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                int v = Integer.parseInt(st.nextToken());
                if(v == 1) map[i][j] = -1;
                else map[i][j] = 0;
            }
        }
        //섬 넘버링
        N = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j] != -1) continue;
                bfs(i, j, ++N);
            }
        }
//        for (int i = 0; i < R; i++) {
//            System.out.println(Arrays.toString(map[i]));
//        }
        // 다리 만들기
        list = new List[N + 1];
        for (int i = 1; i <= N; i++) list[i] = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j] == 0) continue;
                makeBridge(i, j, map[i][j]);
            }
        }
        // MST
        pq.clear();
        visited = new boolean[N + 1];
        answer = 0;
        visited[1] = true;
        pq.addAll(list[1]);
        int cnt = 1;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if(visited[cur.u]) continue;
            visited[cur.u] = true;
            answer += cur.w;
            cnt++;
            for (Node next : list[cur.u]) {
                if(visited[next.u]) continue;
                pq.add(next);
            }
        }
        if(cnt != N) System.out.println(-1);
        else System.out.println(answer);
        br.close();
    }

    static void makeBridge(int r, int c, int v) {
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            int cnt = 0;
            while (true) {
                if(nr < 0 || nr == R || nc < 0 || nc == C || map[nr][nc] == v) break;
                if (map[nr][nc] != 0) {
                    if (cnt >= 2) {
                        list[v].add(new Node(map[nr][nc], cnt));
                        list[map[nr][nc]].add(new Node(v, cnt));
                    }
                    break;
                }
                nr = nr + dr[d];
                nc = nc + dc[d];
                cnt++;
            }
        }
    }
    static void bfs(int r, int c, int v) {
        map[r][c] = v;
        queue = new ArrayDeque<>();
        queue.add(new int[]{r, c});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(nr < 0 || nr == R || nc < 0 || nc == C || map[nr][nc] != -1) continue;
                map[nr][nc] = v;
                queue.add(new int[]{nr, nc});
            }
        }
    }
    static class Node {
        int u, w;
        public Node(int u, int w) {
            this.u = u;
            this.w = w;
        }
    }
}
