package SSAFY.study.week16.t25513;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int count, here, sr, se;
    static final int SIZE = 5;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static Deque<int[]> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        map = new int[SIZE][SIZE];
        visited = new boolean[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        sr = Integer.parseInt(st.nextToken());
        se = Integer.parseInt(st.nextToken());

        count = here = 0;
        queue.add(new int[]{sr, se, 0}); // row, col, move
        visited[sr][se] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (here == 6) {
                count = cur[2];
                break;
            }
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if (nr < 0 || nr == SIZE || nc < 0 || nc == SIZE || visited[nr][nc]
                    || map[nr][nc] == -1) {
                    continue;
                }
                if (here + 1 == map[nr][nc]) {
                    here++;
                    queue.clear();
                    visited = new boolean[SIZE][SIZE];
                    visited[nr][nc] = true;
                    queue.add(new int[]{nr, nc, cur[2] + 1});
                    break;
                }
                visited[nr][nc] = true;
                queue.add(new int[]{nr, nc, cur[2] + 1});
            }
        }
        System.out.println(here == 6 ? count : -1);
        br.close();
    }
}
