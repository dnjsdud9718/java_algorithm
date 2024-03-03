package SSAFY.B형.week2.지뢰찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Solution2 {
    static int N, answer;
    static int[][] map;
    static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
    static Deque<int[]> queue;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                String s = br.readLine();
                for (int j = 0; j < N; j++) {
                    char c = s.charAt(j);
                    if(c == '*') map[i][j] = -1;
                    else map[i][j] = 0;
                }
            }
            answer = 0;
            play();
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
    static void play() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] != 0 || haveBug(i, j)) continue;
                click(i, j);
                answer++;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == 0) answer++;
            }
        }
    }

    static void click(int r, int c) {
        queue = new ArrayDeque<>();
        queue.add(new int[]{r, c});
        map[r][c] = 1;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int d = 0; d < 8; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(nr < 0 || nr == N || nc < 0 || nc == N || map[nr][nc] != 0) continue;
                if (!haveBug(nr, nc)) queue.add(new int[]{nr, nc});
                map[nr][nc] = 1;
            }
        }
    }

    static boolean haveBug(int r, int c) {
        for (int d = 0; d < 8; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(nr < 0 || nr == N || nc < 0 || nc == N) continue;
            if(map[nr][nc] == -1) return true;
        }
        return false;
    }
}
