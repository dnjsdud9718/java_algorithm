package SSAFY.algo.baekjoon.t1194;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 987_654_321;
    static int N, M;
    static char[][] map;
    static int sr, sc;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int answer;
    static Deque<int[]> queue;
    static int[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new int[N][M][1 << 6];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = s.charAt(j);
                if (c == '0') {
                    sr = i;
                    sc = j;
                    map[i][j] = '.';
                } else map[i][j] = c;
            }
        }
//        for (int i = 0; i < N; i++) System.out.println(Arrays.toString(map[i]));
        answer = -1;
        queue = new ArrayDeque<>();
        queue.add(new int[]{sr, sc, 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            System.out.println(Arrays.toString(cur));
            if (map[cur[0]][cur[1]] == '1') {
                System.out.println(Arrays.toString(cur));
                answer = visited[cur[0]][cur[1]][cur[2]];
                break;
            }
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if (nr < 0 || nr == N || nc < 0 || nc == M || visited[nr][nc][cur[2]] != 0 || map[nr][nc] == '#') continue;
                if (map[nr][nc] >= 'A' && map[nr][nc] <= 'F') {
                    if (0 != (cur[2] & (1 << (map[nr][nc] - 'A')))) {
                        visited[nr][nc][cur[2]] = visited[cur[0]][cur[1]][cur[2]] + 1;
                        queue.add(new int[]{nr, nc, cur[2]});
                    }
                } else if (map[nr][nc] >= 'a' && map[nr][nc] <= 'f') {
                    if ((cur[2] & (1 << (map[nr][nc] - 'a'))) == 0) {
//                        여기가 중요하다. 새로운 키가 추가되었을 때,
                        visited[nr][nc][cur[2] | (1 << (map[nr][nc] - 'a'))] = visited[cur[0]][cur[1]][cur[2]] + 1;
                        queue.add(new int[]{nr, nc, cur[2] | (1 << (map[nr][nc] - 'a'))});
                    } else {
                        visited[nr][nc][cur[2]] = visited[cur[0]][cur[1]][cur[2]] + 1;
                        queue.add(new int[]{nr, nc, cur[2]});
                    }
                } else {
                    visited[nr][nc][cur[2]] = visited[cur[0]][cur[1]][cur[2]] + 1;
                    queue.add(new int[]{nr, nc, cur[2]});
                }
            }
        }
        System.out.println(answer);
        br.close();
    }
}
