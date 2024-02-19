package SSAFY.B형.week2.지뢰찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    플러드 필
    파핑파핑 지뢰찾기
    지뢰가 있는 칸을 제외한 다른 모든 칸의 숫자들이 표시되려면 최소 몇 번의 클릭을 해야 하는지 구하는 프로그램
    BFS
    1. 현재 클릭한 곳의 인접한 곳에 지뢰가 하나라도 있다면 나중에 반드시 한 번 클릭해야 하는 위치다.
    2. 현재 클릭한 곳의 인접한 곳에 지뢰가 없다면 확장해 나가야 한다.
    3. 인접한 곳으로 이동하여 다시 주변에 지뢰가 있는지 확인. 있으면 스톱, 없으면 다시 이동.
 */
public class Solution {
    static int ans;
    static int N;
    static int[][] map;
    static int[] dr = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int[] dc = {-1, -1, -1, 0, 0, 1, 1, 1};

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                String mark = br.readLine();
                for (int j = 0; j < N; j++) {
                    char m = mark.charAt(j);
                    if(m == '*') map[i][j] = -2;
                    else map[i][j] = -1;
                }
            }
            ans = 0;
            solve();
            sb.append('#').append(t).append(' ').append(ans).append('\n');
        }
        System.out.println(sb.toString());
        br.close();
    }
    public static void solve() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] != -1) continue; // 지뢰이거나 이미 방문한 적이 있다.
                if (isZero(i, j)) { //
                    click(i, j);
                    ans++;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == -1) ans++;
            }
        }
    }

    public static void click(int i, int j) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{i, j});
        map[i][j] = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int d = 0; d < 8; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                // why check -> map[nr][nc] != -1 -> 이미 방문한 좌표인지 확인.(절대 지뢰일 순 없다. 인접한 애들이 지뢰가 아닌 경우만 큐에 들어가닌까...)
                if(nr < 0 || nr == N || nc < 0 || nc == N || map[nr][nc] != -1) continue;
                if(isZero(nr, nc)) {
                    queue.add(new int[]{nr, nc});
                }
                map[nr][nc] = 0;
            }
        }
    }
    public static boolean isZero(int r, int c) {
        for (int d = 0; d < 8; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(nr < 0 || nr == N || nc < 0 || nc == N) continue;
            if(map[nr][nc] == -2) return false;
        }
        return true;
    }
}
