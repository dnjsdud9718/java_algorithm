package SSAFY.algo.baekjoon.t3109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
    백준 3109 빵집
    백트레킹 문제
    한번 방문한 경우 -> 다시 방문하지 않는다.(그 경로를 통해 목적을 달성했든 안했든)
    .xxxx.
    ..x.x.
    ....x.
    ..xx..

 */
public class Main {
    static int R, C, ans;
    static int[][] src;
    static int[] dr = new int[]{-1, 0, 1};
    static int[] dc = new int[]{1, 1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        src = new int[R][C];
        for (int i = 0; i < R; i++) {
            char[] arr = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (arr[j] == '.') {
                    src[i][j] = 0;
                } else {
                    src[i][j] = 1;
                }
            }
        }

        ans = 0;
        for (int i = 0; i < R; i++) {
            if (dfs(i, 0)) {
                ans++;
            }
        }
        System.out.println(ans);
        br.close();
    }

    public static boolean dfs(int r, int c) {
        if (c == C - 1) {
            return true;
        }
        src[r][c] = 1; // 여기서 방문 처리해도 되고
        for (int i = 0; i < 3; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr < 0 || nr == R || nc < 0 || nc == C || src[nr][nc] != 0) continue;
//            src[nr][nc] = 1; // 여기서 방문 처리해도 되네.
            if(dfs(nr, nc)) return true;
        }
        return false;
    }
}
