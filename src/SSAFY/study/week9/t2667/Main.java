package SSAFY.study.week9.t2667;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, cnt;
    static int[][] map;
    static boolean[][] visited;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
//            System.out.println(Arrays.toString(map[i]));
        }
        cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 0 && !visited[i][j]) {
//                    System.out.println(i+" " + j);
                    pq.add(bfs(i, j));
                    cnt++;
                }
            }
        }
        System.out.println(cnt);

        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
        br.close();
    }

    static int dr[] = {0, 1, 0, -1};
    static int dc[] = {1, 0, -1, 0};
    static int bfs(int r, int c) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{r, c});
        visited[r][c] = true;
        int ret = 1;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(nr < 0 || nr == N || nc < 0 || nc == N || visited[nr][nc]) continue;
                if(map[nr][nc] == 0) continue;
                visited[nr][nc] = true;
                queue.add(new int[]{nr, nc});
//                System.out.println(nr + " " + nc);
                ret++;
            }
        }
        return ret;
    }
}
